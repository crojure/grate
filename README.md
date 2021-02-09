# GRate

Rather than use a JVM language I'm familiar with (Java, Groovy, Kotlin) I decided to use Clojure.

I have no practical experience with Clojure or its tools but thought that this would achieve several goals that using 
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

```
|-------------------------+---------+---------|
|               Namespace | % Forms | % Lines |
|-------------------------+---------+---------|
|               grate.api |   50.00 |   73.33 |
|               grate.cli |   89.47 |  100.00 |
|              grate.core |   12.50 |   66.67 |
| grate.record.comparator |  100.00 |  100.00 |
|     grate.record.loader |  100.00 |  100.00 |
|     grate.record.parser |  100.00 |  100.00 |
| grate.record.repository |  100.00 |  100.00 |
| grate.record.serializer |  100.00 |  100.00 |
|  grate.record.validator |   99.16 |  100.00 |
|-------------------------+---------+---------|
|               ALL FILES |   82.91 |   93.18 |
|-------------------------+---------+---------|
```

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


## API

Base URI: `http://localhost:3000`

`POST /records` - Post single data line of any 3 formats (bar, csv, whitespace)

`GET /records` - Gets JSON array of all records in repository in no particular order

`GET /records/gender` - Gets records sorted by gender then last name

`GET /records/birthdate` - Gets records sorted by birth date

`GET /records/name` - Gets records sorted by last name


## Example record Input

Records can be delimited by bars '|', commas ',' or whitespace.

```
Crone|Todd|M|purple|1960-11-11
Cleavinger,Lynette,F,brown,1979-09-28
Crone Todd M purple 1970-10-10
```

* fields should be ordered `LastName FirstName Gender Favorite Color Date of Birth`
* extra whitespaces are removed
* all fields must have a non-blank value
* Gender can be `M` or `F` only
* Valid colors are: "red" "yellow" "blue" "orange" "green" "violet" "purple" "brown"
* Date of birth must be provided in `YYYY-MM-DD` format including


## Example CLI Output
```
*** Sort by gender and last name asc ***
Smith,Jennifer,F,blue,7/8/1808
Adams,Quincy,M,green,1/11/1909
Crone,Todd,M,purple,10/10/1970
*** Sort by birth date asc ***
Smith,Jennifer,F,blue,7/8/1808
Adams,Quincy,M,green,1/11/1909
Crone,Todd,M,purple,10/10/1970
*** Sort by last name desc ***
Smith,Jennifer,F,blue,7/8/1808
Crone,Todd,M,purple,10/10/1970
Adams,Quincy,M,green,1/11/1909
```

## Known Issues

I'm a noob with Clojure/Compojure so have not figured out why POSTing nothing to POST endpoint
returns 500 because `Cannot open <nil> as a Reader.` .  Didn't spend much time investigating as I'm sure its a noob mistake and returning
400 in this case is easy likely enough.