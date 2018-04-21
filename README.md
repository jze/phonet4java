# phonet4java

[![travis-ci](https://api.travis-ci.org/jze/phonet4java.svg?branch=master)](https://travis-ci.org/jze/phonet4java)
[![codecov](https://codecov.io/gh/jze/phonet4java/branch/master/graph/badge.svg)](https://codecov.io/gh/jze/phonet4java)

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

To use the library you can simply add a dependency to `phonet4java` like this:

	<dependency>
		<groupId>de.zedlitz</groupId>
		<artifactId>phonet4java</artifactId>
		<version>1.1</version>
	</dependency>
