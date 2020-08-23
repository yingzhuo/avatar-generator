[![License](http://img.shields.io/badge/License-Apache_2-red.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)
[![JDK](http://img.shields.io/badge/JDK-v8.0-yellow.svg)](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
[![Build](http://img.shields.io/badge/Build-Maven_2-green.svg)](https://maven.apache.org/)
[![Maven Central](https://img.shields.io/maven-central/v/com.github.yingzhuo/avatar-generator.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22com.github.yingzhuo%22%20AND%20a:%22avatar-generator%22)

# avatar-generator

### 依赖

```xml
<dependency>
    <groupId>com.github.yingzhuo</groupId>
    <artifactId>avatar-generator</artifactId>
    <version>${version}</version>
</dependency>
```
### 使用

```java
public static void main(String[] args) {
    final Avatar avatar = IdenticonAvatar.newAvatarBuilder()
                    .layers(new ColorPaintBackgroundLayer(Color.WHITE))
                    .build();
            
    final BufferedImage image = avatar.create(123456L);
}
```

### 作者

* 应卓 - [github](https://github.com/yingzhuo)
