# Grate

Guarantee Rate Homework

Rather than use a JVM language I'm familiar with (Java, Groovy, Kotlin) I decided to use Clojure.

I have not practical experience with Clojure or its tools but thought that this would achieve several goals that using 
familiar languages and tools would not.

1. Fun! - Coding off hours is more fun using stuff you don't use in your day job.
1. Familiarity - Using Clojure for this helps me figure out if I would enjoy using on a daily basis.
1. Flexibility - Show I can spend a weekend using Clojure tools and become effective.

## Usage

### Leiningen

Run tests
`lein test`

Check test coverage (via Cloverage plugin)
`lein cloverage`

Run server with API
`lein run`

Run CLI
`lein run cli [file-location]`


### Build and run with JVM

Build UberJar:

`lein uberjar`

API
`java -jar target/uberjar/grate-0.1.0-SNAPSHOT-standalone.jar`

CLI
`java -jar target/uberjar/grate-0.1.0-SNAPSHOT-standalone.jar cli [file-location]`


## Example CLI Output
```
*** Output 1 ***
Smith,Jennifer,F,blue,7/8/1808
Adams,Quincy,M,green,1/11/1909
Crone,Todd,M,hazel,10/10/1970
*** Output 2 ***
Smith,Jennifer,F,blue,7/8/1808
Adams,Quincy,M,green,1/11/1909
Crone,Todd,M,hazel,10/10/1970
*** Output 3 ***
Smith,Jennifer,F,blue,7/8/1808
Crone,Todd,M,hazel,10/10/1970
Adams,Quincy,M,green,1/11/1909
```