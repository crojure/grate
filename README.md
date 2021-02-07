# Grate

Guarantee Rate Homework
Step #1 - Command line app

## Usage

Using Leiningen:

API
`lein run`

CLI
`lein run cli [file-location]`

User UberJar:

`lein uberjar`

API
`java -jar target/uberjar/grate-0.1.0-SNAPSHOT-standalone.jar`

CLI
`java -jar target/uberjar/grate-0.1.0-SNAPSHOT-standalone.jar cli [file-location]`


## Example Output
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