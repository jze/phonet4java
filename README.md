# phonet4java

![Maven Central version](https://img.shields.io/maven-central/v/de.zedlitz/phonet4java)
![Coverage](.github/badges/jacoco.svg)

The project provides Java implementations for several phonetic algorithms like:

  * Soundex
  * refined Soundex
  * Kölner Phonetik
  * Daitch-Mokotoff
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
		<version>1.2.0</version>
	</dependency>

## Example

Here are some example to get an impression how the different algorithms work:

| input | Soundex | ref. Soundex | Daitch-Mokotoff | Kölner Phonetik | Phonet | Phonet (var. 2) |
| ----- | ------- | ------------ | --------------- | --------------- | ------ | --------------- |
| Zedlitz | Z343 | Z6765 | 438400 | 8258 | ZETLIZ | ZETLIZ | 
| Zetlitz | Z343 | Z6765 | 438400 | 8258 | ZETLIZ | ZETLIZ | 
| Zedtlitz | Z343 | Z6765 | 438400 | 8258 | ZETLIZ | ZETLIZ | 
| Meyer | M600 | M9000 | 619000 | 67 | MEIA | NEIA | 
| Meier | M600 | M9000 | 619000 | 67 | MEIA | NEIA | 
| Mayr | M600 | M9000 | 690000 | 67 | MEIA | NEIA | 
| Mayer | M600 | M9000 | 619000 | 67 | MEIA | NEIA | 
| Kiew | K000 | K0000 | 570000 | 43 | KIF | KIF | 
| Kyjiw | K200 | K4000 | 517000 | 43 | KÜIF | KIF | 
| Kyiv | K100 | K2000 | 570000 | 43 | KÜIF | KIF | 
| Kyiv | K100 | K2000 | 570000 | 43 | KÜIF | KIF | 
| Kiev | K100 | K2000 | 570000 | 43 | KIW | KIF | 
| Szczypiorskowski | S162 | S5351 | 279457 | 81784384 | SHÜPIORSKOFSKI | ZIPIURZKUFZKI | 
