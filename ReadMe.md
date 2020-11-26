[![Build Status](https://travis-ci.org/VladislavSevruk/TestDataGenerator.svg?branch=master)](https://travis-ci.com/VladislavSevruk/TestDataGenerator)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=VladislavSevruk_TestDataGenerator&metric=alert_status)](https://sonarcloud.io/dashboard?id=VladislavSevruk_TestDataGenerator)
[![Code Coverage](https://sonarcloud.io/api/project_badges/measure?project=VladislavSevruk_TestDataGenerator&metric=coverage)](https://sonarcloud.io/component_measures?id=VladislavSevruk_TestDataGenerator&metric=coverage)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.vladislavsevruk/test-data-generator/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.vladislavsevruk/test-data-generator)

# Test Data Generator
This utility library helps to generate testing data for POJOs using various settings.

## Table of contents
* [Getting started](#getting-started)
  * [Maven](#maven)
  * [Gradle](#gradle)
* [Usage](#usage)
  * [Non-parameterized classes](#non-parameterized-classes)
  * [Parameterized classes](#parameterized-classes)
* [Customization](#customization)
  * [Adding custom test data generators](#adding-custom-test-data-generators)
  * [Adding custom field mappings](#adding-custom-field-mappings)
* [License](#license)

## Getting started
To add library to your project perform next steps:

### Maven
Add the following dependency to your pom.xml:
```xml
<dependency>
      <groupId>com.github.vladislavsevruk</groupId>
      <artifactId>test-data-generator</artifactId>
      <version>1.0.0</version>
</dependency>
```
### Gradle
Add the following dependency to your build.gradle:
```groovy
implementation 'com.github.vladislavsevruk:test-data-generator:1.0.0'
```

## Usage
TestDataGenerator uses POJO's public constructors and setters to create POJO and set generated preudorandom values to it. 
Setters name can be in both classic and fluent styles, e.g. field with name ``field`` can match setters with 
``setField`` or ``field`` names and field type  should match parameter type of setter. You can 
[add your custom generator](#adding-custom-test-data-generators) if you want to set custom generator logic or override 
logic of existent one or you can [add custom generation logic for specific field](#adding-custom-field-mappings).

### Non-parameterized classes
Let's assume that we have following POJO:
```java
public class TestModel {
    private Set<String> field1;
    private Integer field2;

    // getters and setters
}
```

All you have to do is to use ``TestDataGenerator.generate(Class<T>)`` method:
```kotlin
// generating POJO with pseudorandom values
TestModel testModel = new TestDataGenerator().generate(TestModel.class);
// verifying field values
Assertions.assertNotEquals(0, acceptorModel.getField1().size());
Assertions.assertNotNull(acceptorModel.getField1().get(0));
Assertions.assertNotNull(acceptorModel.getField2());
```

### Parameterized classes
Let's assume that we have following generic POJO:
```java
public class GenericModel<T> {

    private Set<String> field1;
    private T field2;

    // getters and setters
}
```

All you have to do is to use ``TestDataGenerator.generate(TypeProvider<T>)`` method:
```kotlin
// generating POJO with pseudorandom values
GenericModel<String> acceptorModel = new TestDataGenerator()
        .generate(new TypeProvider<GenericModel<String>>() {});
// verifying field values
Assertions.assertNotEquals(0, acceptorModel.getField1().size());
Assertions.assertNotNull(acceptorModel.getField1().get(0));
Assertions.assertNotNull(acceptorModel.getField2());
```

## Customization
### Adding custom test data generators
If you want to set custom generation logic for any type or override logic of existent one you can implement 
[NonParameterizedTypeDataGenerator](/src/main/java/com/github/vladislavsevruk/generator/test/data/generator/NonParameterizedTypeDataGenerator.java) 
for non-parameterized type or 
[ParameterizedTypeDataGenerator](/src/main/java/com/github/vladislavsevruk/generator/test/data/generator/ParameterizedTypeDataGenerator.java) 
for parameterized type and add it to 
[TestDataGeneratorStorage](/src/main/java/com/github/vladislavsevruk/generator/test/data/storage/TestDataGeneratorStorage.java) 
using one of ``add*`` methods:
```kotlin
TestDataGenerationContextManager.getContext().getTestDataGeneratorStorage()
        .add(new SomeCustomTestDataGenerator());
```

### Adding custom field mappings
If you want to set custom generation logic for any field you can add it to 
[CustomFieldMappingStorage](/src/main/java/com/github/vladislavsevruk/generator/test/data/mapping/CustomFieldMappingStorage.java) 
using ``addMapping`` method:
```kotlin
Field field = TestModel.class.getDeclaredField("field2");
TestDataGenerationContextManager.getContext().getCustomFieldMappingStorage()
        .addMapping(field, config -> 15);
```

## License
This project is licensed under the MIT License, you can read the full text [here](LICENSE).