# HumanNameParse.java

Java port Author: Bruno P. Kinoshita

Contributors:     Dan G. Switzer, II

Original library Author: Jason Priem jason@jasonpriem.com (credits go to him)
Original library Author Website: http://jasonpriem.com/human-name-parse

License: [MIT](http://www.opensource.org/licenses/mit-license.php)

## Description
Takes human names of arbitrary complexity and various wacky formats like:

* J. Walter Weatherman
* de la Cruz, Ana M.
* James C. ('Jimmy') O'Dell, Jr.

and parses out the:

* title (Like "Mrs." in "Jane Doe")
* leading initial (Like "J." in "J. Walter Weatherman")
* first name (or first initial in a name like 'R. Crumb')
* nicknames (like "Jimmy" in "James C. ('Jimmy') O'Dell, Jr.")
* middle names
* last name (including compound ones like "van der Sar' and "Ortega y Gasset"), and
* suffix (like 'Jr.', 'III')

## Usage

```
<dependencies>
  <dependency>
    <groupId>com.tupilabs</groupId>
    <artifactId>human-name-parser</artifactId>
  </dependency>
</dependencies>
```

```
-- Method 1 - Using the Name object
Name object = new Name("Dr. J. Björn (\"JB\") Charles O'Malley y Muñoz, Jr.");
HumanNameParserParser parser = new HumanNameParserParser(object);

-- Method 2 - Pass a String to the parser
HumanNameParserParser parser = new HumanNameParserParser("Dr. J. Björn (\"JB\") Charles O'Malley y Muñoz, Jr.");

-- parser methods
String title = parser.getTitle();                  // Dr.
String leadingInit = parser.getLeadingInit();      // J.
String first = parser.getFirst();                  // Björn
String fullFirst = parser.getFullFirst();          // J. Björn
String nicknames = parser.getNicknames();          // JB
String middle = parser.getMiddle();                // Charles
String last = parser.getLast();                    // O'Malley y Muñoz
String suffix = parser.getSuffix();                // Jr.
String fullLast = parser.getFullLast();            // O'Malley y Muñoz, Jr.
```