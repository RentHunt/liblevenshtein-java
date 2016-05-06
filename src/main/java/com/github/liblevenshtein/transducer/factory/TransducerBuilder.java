package com.github.liblevenshtein.transducer.factory;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import com.github.liblevenshtein.collection.dictionary.Dawg;
import com.github.liblevenshtein.collection.dictionary.DawgNode;
import com.github.liblevenshtein.collection.dictionary.factory.DawgFactory;
import com.github.liblevenshtein.transducer.Algorithm;
import com.github.liblevenshtein.transducer.IDistanceFunction;
import com.github.liblevenshtein.transducer.IState;
import com.github.liblevenshtein.transducer.ITransducer;
import com.github.liblevenshtein.transducer.MergeFunction;
import com.github.liblevenshtein.transducer.StandardPositionComparator;
import com.github.liblevenshtein.transducer.StandardPositionDistanceFunction;
import com.github.liblevenshtein.transducer.SubsumesFunction;
import com.github.liblevenshtein.transducer.Transducer;
import com.github.liblevenshtein.transducer.TransducerAttributes;
import com.github.liblevenshtein.transducer.UnsubsumeFunction;
import com.github.liblevenshtein.transducer.XPositionComparator;
import com.github.liblevenshtein.transducer.XPositionDistanceFunction;

/**
 * Fluently-builds Levenshtein transducers.
 * @author Dylon Edwards
 * @since 2.1.0
 */
@Slf4j
public class TransducerBuilder implements ITransducerBuilder, Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * Format of error messages about unsupported algorithms.
   */
  private static final String UNSUPPORTED_ALGORITHM = "Unsupported Algorithm: ";

  /**
   * Builds DAWG collections from dictionaries.
   */
  private final DawgFactory dawgFactory = new DawgFactory();

  /**
   * Dictionary automaton for seeking spelling candidates.
   */
  @SuppressWarnings("unchecked")
  @Setter(onMethod = @__({@Override}))
  private Collection<String> dictionary = Collections.EMPTY_LIST;

  /**
   * Whether {@link #dictionary} is sorted.
   */
  @Setter
  private boolean isSorted = false;

  /**
   * Desired Levenshtein algorithm for searching.
   * -- SETTER --
   * Desired Levenshtein algorithm for searching.
   * @param algorithm Desired Levenshtein algorithm for searching.
   * @return This {@link TransducerBuilder} for fluency.
   */
  @NonNull
  @Setter(onMethod = @__({@Override}))
  private Algorithm algorithm = Algorithm.STANDARD;

  /**
   * Default maximum number of errors tolerated between each spelling candidate
   * and the query term.
   * -- SETTER --
   * Default maximum number of errors tolerated between each spelling candidate
   * and the query term.
   * @param defaultMaxDistance Default maximum number of errors tolerated
   * between each spelling candidate and the query term.
   * @return This {@link TransducerBuilder} for fluency.
   */
  @Setter(onMethod = @__({@Override}))
  private int defaultMaxDistance = 2;

  /**
   * Whether the distances between each spelling candidate and the query term
   * should be included in the collections of spelling candidates.
   * -- SETTER --
   * Whether the distances between each spelling candidate and the query term
   * should be included in the collections of spelling candidates.
   * @param includeDistance Whether the distances between each spelling
   * candidate and the query term should be included in the collections of
   * spelling candidates.
   * @return This {@link TransducerBuilder} for fluency.
   */
  @Setter(onMethod = @__({@Override}))
  private boolean includeDistance = true;

  /**
   * {@inheritDoc}
   */
  @Override
  public ITransducerBuilder dictionary(
      @NonNull final Collection<String> dictionary,
      final boolean isSorted) {
    this.dictionary = dictionary;
    this.isSorted = isSorted;
    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("unchecked")
  public <CandidateType> ITransducer<CandidateType> build() {
    log.info("Building transducer out of [{}] terms with isSorted [{}], "
        + "algorithm [{}], defaultMaxDistance [{}], and includeDistance [{}]",
        dictionary.size(), isSorted, algorithm, defaultMaxDistance,
        includeDistance);

    final Dawg dictionary = dawgFactory.build(this.dictionary, this.isSorted);

    final IStateFactory stateFactory =
      new StateFactory().elementFactory(new ElementFactory<int[]>());

    final TransducerAttributes<DawgNode, CandidateType> attributes =
      new TransducerAttributes<DawgNode, CandidateType>()
        .maxDistance(defaultMaxDistance)
        .stateTransitionFactory(buildStateTransitionFactory(stateFactory))
        .candidateFactory(
          (ICandidateFactory<CandidateType>)
          (includeDistance
            ? new CandidateFactory.WithDistance()
            : new CandidateFactory.WithoutDistance()))
        .intersectionFactory(new IntersectionFactory<DawgNode>())
        .minDistance(buildMinDistance())
        .isFinal(dawgFactory.isFinal(dictionary))
        .dictionaryTransition(dawgFactory.transition(dictionary))
        .initialState(buildInitialState(stateFactory))
        .dictionaryRoot(dictionary.root())
        .dictionary(dictionary)
        .algorithm(algorithm)
        .includeDistance(includeDistance);

    return new Transducer<>(attributes);
  }

  /**
   * Builds the function that finds the distance between spelling candidates and
   * the query term.
   * @return Levenshtein algorithm-specific, distance function.
   */
  protected IDistanceFunction buildMinDistance() {
    switch (algorithm) {
      case STANDARD:
        return new StandardPositionDistanceFunction();
      case TRANSPOSITION: // fall through
      case MERGE_AND_SPLIT:
        return new XPositionDistanceFunction();
      default:
        throw new IllegalArgumentException(UNSUPPORTED_ALGORITHM + algorithm);
    }
  }

  /**
   * Builds the initial state from which to begin searching the dictionary
   * automaton for spelling candidates.
   * @param stateFactory Builds and recycles Levenshtein states.
   * @return Start state for traversing the dictionary automaton.
   */
  protected IState buildInitialState(@NonNull final IStateFactory stateFactory) {
    switch (algorithm) {
      case STANDARD:
        return stateFactory.build(new int[] {0, 0});
      case TRANSPOSITION: // fall through
      case MERGE_AND_SPLIT:
        return stateFactory.build(new int[] {0, 0, 0});
      default:
        throw new IllegalArgumentException(UNSUPPORTED_ALGORITHM + algorithm);
    }
  }

  /**
   * Builds a state-transition factory from the parameters specified at the time
   * {@link #build()} was called.
   * @param stateFactory Builds Levenshtein states.
   * @return New state-transition factory.
   */
  protected IStateTransitionFactory buildStateTransitionFactory(
      @NonNull final IStateFactory stateFactory) {

    final StateTransitionFactory stateTransitionFactory =
      new StateTransitionFactory().stateFactory(stateFactory);

    final PositionTransitionFactory positionTransitionFactory;
    final IPositionFactory positionFactory;

    switch (algorithm) {
      case STANDARD:
        positionTransitionFactory =
          new PositionTransitionFactory.ForStandardPositions();
        positionFactory = new PositionFactory.ForStandardPositions();
        stateTransitionFactory
          .comparator(new StandardPositionComparator())
          .positionTransitionFactory(positionTransitionFactory)
          .merge(new MergeFunction.ForStandardPositions()
              .positionFactory(positionFactory))
          .unsubsume(new UnsubsumeFunction.ForStandardPositions()
              .subsumes(new SubsumesFunction.ForStandardAlgorithm())
              .positionFactory(positionFactory));
        break;
      case TRANSPOSITION:
        positionTransitionFactory =
          new PositionTransitionFactory.ForTranspositionPositions();
        positionFactory = new PositionFactory.ForXPositions();
        stateTransitionFactory
          .comparator(new XPositionComparator())
          .positionTransitionFactory(positionTransitionFactory)
          .merge(new MergeFunction.ForXPositions()
              .positionFactory(positionFactory))
          .unsubsume(new UnsubsumeFunction.ForXPositions()
              .subsumes(new SubsumesFunction.ForTransposition())
              .positionFactory(positionFactory));
        break;
      case MERGE_AND_SPLIT:
        positionTransitionFactory =
          new PositionTransitionFactory.ForMergeAndSplitPositions();
        positionFactory = new PositionFactory.ForXPositions();
        stateTransitionFactory
          .comparator(new XPositionComparator())
          .positionTransitionFactory(positionTransitionFactory)
          .merge(new MergeFunction.ForXPositions()
              .positionFactory(positionFactory))
          .unsubsume(new UnsubsumeFunction.ForXPositions()
              .subsumes(new SubsumesFunction.ForMergeAndSplit())
              .positionFactory(positionFactory));
        break;
      default:
        throw new IllegalArgumentException(UNSUPPORTED_ALGORITHM + algorithm);
    }

    positionTransitionFactory
      .stateFactory(stateFactory)
      .positionFactory(positionFactory);

    return stateTransitionFactory;
  }
}