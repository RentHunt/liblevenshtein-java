[Index](index.md) > [Installation](installation.md)

# Installation

## Latest, Development Release

Add a Maven dependency on [Artifactory][artifactory-repo].  For example, in a
[Gradle][gradle-home] project, you would modify your `repositories` as follows:

```groovy
repositories {
  maven {
    url 'https://oss.jfrog.org/artifactory/oss-release-local'
  }
}
```

## Latest, Stable Release

Add a Maven dependency on one of the following:
- [Maven Central][maven-repo]
- [JCenter][jcenter-repo]
- [Bintray][bintray-repo]

## Maven

```xml
<dependency>
  <groupId>com.github.universal-automata</groupId>
  <artifactId>liblevenshtein</artifactId>
  <version>3.0.0</version>
</dependency>
```

## Apache Buildr

```ruby
'com.github.universal-automata:liblevenshtein:jar:3.0.0'
```

## Apache Ivy

```xml
<dependency org="com.github.universal-automata" name="liblevenshtein" rev="3.0.0" />
```

## Groovy Grape

```groovy
@Grapes(
@Grab(group='com.github.universal-automata', module='liblevenshtein', version='3.0.0')
)
```

## Gradle / Grails

```groovy
compile 'com.github.universal-automata:liblevenshtein:3.0.0'
```

## Scala SBT

```scala
libraryDependencies += "com.github.universal-automata" % "liblevenshtein" % "3.0.0"
```

## Leiningen

```clojure
[com.github.universal-automata/liblevenshtein "3.0.0"]
```

## Git

```
% git clone --progress git@github.com:universal-automata/liblevenshtein-java.git
Cloning into 'liblevenshtein-java'...
remote: Counting objects: 8120, done.        
remote: Compressing objects: 100% (475/475), done.        
remote: Total 8120 (delta 354), reused 0 (delta 0), pack-reused 7619        
Receiving objects: 100% (8120/8120), 5.52 MiB | 213.00 KiB/s, done.
Resolving deltas: 100% (5368/5368), done.
Checking connectivity... done.

% cd liblevenshtein-java
% git pull --progress
Already up-to-date.

% git fetch --progress --tags
% git checkout --progress 3.0.0
Note: checking out '3.0.0'.

You are in 'detached HEAD' state. You can look around, make experimental
changes and commit them, and you can discard any commits you make in this
state without impacting any branches by performing another checkout.

If you want to create a new branch to retain commits you create, you may
do so (now or later) by using -b with the checkout command again. Example:

  git checkout -b <new-branch-name>

HEAD is now at f6e0a77... Automatically-generated documentation for version 3.0.0

% git submodule init
% git submodule update

```

Then, [build the library](building.md).

[liblevenshtein-java][github-repo] is maintained by[@dylon][github-author] ([dylon.devo+liblevenshtein-java@gmail.com][github-email])

[coursera-automata]: https://class.coursera.org/automata "Jeffrey Ullman (Coursera)"
[coursera-compilers]: https://class.coursera.org/compilers "Alex Aiken (Coursera)"
[coursera-nlp]: https://class.coursera.org/nlp "Dan Jurafsky and Chris Manning (Coursera)"
[damn-cool-algos-levenshtein-automata-2010]: http://blog.notdot.net/2010/07/Damn-Cool-Algorithms-Levenshtein-Automata "Nick Johnson (2010)"
[dict-compress-dawg-2011]: http://stevehanov.ca/blog/index.php?id=115 "Steve Hanov (2011)"
[fast-easy-correct-trie-2011]: http://stevehanov.ca/blog/index.php?id=114 "Steve Hanov (2011)"
[fast-string-correction-2002]: http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.16.652 "Klaus Schulz and Stoyan Mihov (2002)"
[incremental-construction-dawg-2000]: http://dl.acm.org/citation.cfm?id=971842 "Jan Daciuk, Bruce W. Watson, Stoyan Mihov, and Richard E. Watson (2000)"
[klaus-schulz]: http://www.cis.uni-muenchen.de/people/schulz.html "Klaus Schulz"
[lucene-fuzzy-2011]: http://blog.mikemccandless.com/2011/03/lucenes-fuzzyquery-is-100-times-faster.html "Michael McCandless (2011)"
[moman]: https://sites.google.com/site/rrettesite/moman "Moman"
[rao-li]: http://www.usca.edu/math/~mathdept/rli/ "Dr. Rao Li"
[stoyan-mihov]: http://www.lml.bas.bg/~stoyan/ "Stoyan Mihov"
[universal-automata-2005]: http://www.fmi.uni-sofia.bg/fmi/logic/theses/mitankin-en.pdf "Petar Nikolaev Mitankin (2005)"
[usca]: http://web.usca.edu/ "University of South Carolina Aiken"

[live-demo]: http://universal-automata.github.io/liblevenshtein/

[github-author]: https://github.com/dylon "Dylon Edwards <dylon.devo+liblevenshtein-java@gmail.com>"
[github-demo]: http://universal-automata.github.io/liblevenshtein/ "liblevenshtein demo"
[github-email]: mailto:dylon.devo+liblevenshtein-java@gmail.com "Dylon Edwards <dylon.devo+liblevenshtein-java@gmail.com>"
[github-repo]: https://github.com/universal-automata/liblevenshtein-java/ "universal-automata/liblevenshtein-java"

[wikipedia-damerau-levenshtein-distance]: https://en.wikipedia.org/wiki/Damerau%E2%80%93Levenshtein_distance "Damerau–Levenshtein distance"
[wikipedia-levenshtein-distance]: https://en.wikipedia.org/wiki/Levenshtein_distance "Levenshtein distance"

[master-branch]: https://github.com/universal-automata/liblevenshtein-java/tree/master "universal-automata/liblevenshtein-java/master"
[release-branch]: https://github.com/universal-automata/liblevenshtein-java/tree/release "universal-automata/liblevenshtein-java/release"
[release-branch-3.x]: https://github.com/universal-automata/liblevenshtein-java/tree/release-3.x "universal-automata/liblevenshtein-java/release-3.x"
[release-branch-2.x]: https://github.com/universal-automata/liblevenshtein-java/tree/release-2.x "universal-automata/liblevenshtein-java/release-2.x"

[wiki]: https://github.com/universal-automata/liblevenshtein-java/blob/gh-pages/docs/wiki/3.0.0/index.md "liblevenshtein 3.0.0 Wiki"
[javadoc]: http://universal-automata.github.io/liblevenshtein-java/docs/javadoc/3.0.0/index.html "liblevenshtein 3.0.0 API"
[tagged-source]: https://github.com/universal-automata/liblevenshtein-java/tree/3.0.0/src "liblevenshtein 3.0.0"

[java-lib]: https://github.com/universal-automata/liblevenshtein-java "liblevenshtein-java"
[java-cli]: https://github.com/universal-automata/liblevenshtein-java-cli "liblevenshtein-java-cli"
[java-cli-readme]: https://github.com/universal-automata/liblevenshtein-java-cli/blob/master/README.md "liblevenshtein-java-cli, README.md"

[javadoc/Iterable]: https://docs.oracle.com/javase/8/docs/api/java/lang/Iterable.html?is-external=true "java.lang.Iterable"
[javadoc/Iterator.next()]: https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html#next-- "java.util.Iterator.next()"
[javadoc/Iterator]: https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html "java.util.Iterator"
[javadoc/String]: https://docs.oracle.com/javase/8/docs/api/java/lang/String.html "java.lang.String"

[javadoc/Algorithm.MERGE_AND_SPLIT]: http://universal-automata.github.io/liblevenshtein-java/docs/javadoc/3.0.0/com/github/liblevenshtein/transducer/Algorithm.html#MERGE_AND_SPLIT "Algorithm.MERGE_AND_SPLIT"
[javadoc/Algorithm.STANDARD]: http://universal-automata.github.io/liblevenshtein-java/docs/javadoc/3.0.0/com/github/liblevenshtein/transducer/Algorithm.html#STANDARD "Algorithm.STANDARD"
[javadoc/Algorithm.TRANSPOSITION]: http://universal-automata.github.io/liblevenshtein-java/docs/javadoc/3.0.0/com/github/liblevenshtein/transducer/Algorithm.html#TRANSPOSITION "Algorithm.TRANSPOSITION"
[javadoc/ITransducer.transduce(String)]: http://universal-automata.github.io/liblevenshtein-java/docs/javadoc/3.0.0/com/github/liblevenshtein/transducer/ITransducer.html#transduce-java.lang.String- "ITransducer.transduce(String):Iterable"
[javadoc/ITransducer.transduce(String,int)]: http://universal-automata.github.io/liblevenshtein-java/docs/javadoc/3.0.0/com/github/liblevenshtein/transducer/ITransducer.html#transduce-java.lang.String-int- "ITransducer.transduce(String,int):Iterable"
[javadoc/MemoizedMergeAndSplit.between(String,String)]: http://universal-automata.github.io/liblevenshtein-java/docs/javadoc/3.0.0/com/github/liblevenshtein/distance/MemoizedMergeAndSplit.html "MemoizedMergeAndSplit.between(String,String):int"
[javadoc/MemoizedStandard.between(String,String)]: http://universal-automata.github.io/liblevenshtein-java/docs/javadoc/3.0.0/com/github/liblevenshtein/distance/MemoizedStandard.html "MemoizedStandard.between(String,String):int"
[javadoc/MemoizedTransposition.between(String,String)]: http://universal-automata.github.io/liblevenshtein-java/docs/javadoc/3.0.0/com/github/liblevenshtein/distance/MemoizedTransposition.html "MemoizedTransposition.between(String,String):int"
[javadoc/TransducerBuilder.algorithm(Algorithm)]: http://universal-automata.github.io/liblevenshtein-java/docs/javadoc/3.0.0/com/github/liblevenshtein/transducer/factory/TransducerBuilder.html#algorithm-com.github.liblevenshtein.Algorithm- "TransducerBuilder.algorithm(Algorithm):TransducerBuilder"
[javadoc/TransducerBuilder.build()]: http://universal-automata.github.io/liblevenshtein-java/docs/javadoc/3.0.0/com/github/liblevenshtein/transducer/factory/TransducerBuilder.html#build-- "TransducerBuilder.build():ITransducer"
[javadoc/TransducerBuilder.defaultMaxDistance(int)]: http://universal-automata.github.io/liblevenshtein-java/docs/javadoc/3.0.0/com/github/liblevenshtein/transducer/factory/TransducerBuilder.html#defaultMaxDistance-int- "TransducerBuilder.defaultMaxDistance(int):TransducerBuilder"
[javadoc/TransducerBuilder.dictionary(Collection)]: http://universal-automata.github.io/liblevenshtein-java/docs/javadoc/3.0.0/com/github/liblevenshtein/transducer/factory/TransducerBuilder.html#dictionary-java.util.Collection- "TransducerBuilder.dictionary(Collection):TransducerBuilder"
[javadoc/TransducerBuilder.dictionary(Collection,boolean)]: http://universal-automata.github.io/liblevenshtein-java/docs/javadoc/3.0.0/com/github/liblevenshtein/transducer/factory/TransducerBuilder.html#dictionary-java.util.Collection-boolean- "TransducerBuilder.dictionary(Collection,boolean):TransducerBuilder"
[javadoc/TransducerBuilder.includeDistance(boolean)]: http://universal-automata.github.io/liblevenshtein-java/docs/javadoc/3.0.0/com/github/liblevenshtein/transducer/factory/TransducerBuilder.html#includeDistance-boolean- "TransducerBuilder.includeDistance(boolean):TransducerBuilder"

[src/Candidate]: https://github.com/universal-automata/liblevenshtein-java/blob/master/src/main/java/com/github/liblevenshtein/transducer/Candidate.java "Candidate.java"
[src/ITransducer]: https://github.com/universal-automata/liblevenshtein-java/blob/3.0.0/src/main/java/com/github/liblevenshtein/transducer/factory/TransducerBuilder.java "TransducerBuilder.java"
[src/TransducerBuilder.java]: https://github.com/universal-automata/liblevenshtein-java/blob/3.0.0/src/main/java/com/github/liblevenshtein/transducer/factory/TransducerBuilder.java "TransducerBuilder.java"
[src/build.gradle]: https://github.com/universal-automata/liblevenshtein-java/blob/3.0.0/build.gradle "build.gradle"

[top-20-most-common-english-words.txt]: https://raw.githubusercontent.com/universal-automata/liblevenshtein-java/3.0.0/src/test/resources/top-20-most-common-english-words.txt "top-20-most-common-english-words.txt"

[maven-repo]: https://repo1.maven.org/maven2 "Maven Central repository"
[jcenter-repo]: https://jcenter.bintray.com "JCenter repository"
[bintray-repo]: https://dl.bintray.com/universal-automata/liblevenshtein "Bintray repository"
[artifactory-repo]: https://oss.jfrog.org/artifactory/oss-release-local "Artifactory repository"

[gradle-home]: http://gradle.org/ "Gradle homepage"