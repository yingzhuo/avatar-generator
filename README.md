[![License](http://img.shields.io/badge/License-Apache_2-red.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)
[![JDK](http://img.shields.io/badge/JDK-v8.0-yellow.svg)](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
[![Build](http://img.shields.io/badge/Build-Maven_2-green.svg)](https://maven.apache.org/)

# avatar-generator

### 用法

```xml
<dependency>
    <groupId>com.github.yingzhuo</groupId>
    <artifactId>avatar-generator</artifactId>
    <version>1.0.0</version>
</dependency>
```

```java
final Avatar avatar = IdenticonAvatar.newAvatarBuilder()
                .layers(new ColorPaintBackgroundLayer(Color.WHITE))
                .build();
        
final BufferedImage image = avatar.create(123456L);
```

### 作者

* 应卓 - [github](https://github.com/yingzhuo)
