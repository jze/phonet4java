# phonet4java
The project provides Java implementations for several phonetic algorithms like:

  * Soundex
  * Metaphone
  * Kölner Phonetik
  * Phonet

## Usage

The usage is very simple. Just create an instance of the required encoder and invoke the `code` method. Here is a simple example.

	Coder c = new Phonet2();
	c.code("Kiel");

### Maven
Since the library is not included in the central Maven repository, you have to add a repository to your project:

	<repositories>
          <repository>
            <id>genealogy_net</id>
            <url>http://files.genealogy.net/maven2/</url>
        </repository>
    </repositories>

Now you can add a dependency to `phonet4java` like this:

	<dependency>
		<groupId>de.zedlitz</groupId>
		<artifactId>phonet4java</artifactId>
		<version>1.1</version>
	</dependency>
