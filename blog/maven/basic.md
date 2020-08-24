#### dependencyManagement与dependencies区别
```
dependencyManagement元素。通过它元素来管理jar包的版本，
让子项目中引用一个依赖而不用显示的列出版本号。Maven会沿着父子层次向上走，
直到找到一个拥有dependencyManagement元素的项目，
然后它就会使用在这个dependencyManagement元素中指定的版本号。

```
> 父pom中dependencyManagement如下
```
<modules>
        <module>module1</module>
</modules>
<properties>
        <spring-version>3.1.1.RELEASE</spring-version>
</properties>

<dependencyManagement>
      <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-web</artifactId>
            <version>${spring-version}</version>
      </dependency>
</dependencyManagement>
```
>
```
<dependencies>
        <dependency>
              <groupId>org.springframework</groupId>
              <artifactId>spring-web</artifactId>
        </dependency>
</dependencies>
```
> 这样做的好处：统一管理项目的版本号，确保应用的各个项目的依赖和版本一致，  
才能保证测试的和发布的是相同的成果，因此，在顶层pom中定义共同的依赖关系。  
同时可以避免在每个使用的子项目中都声明一个版本号，这样想升级或者切换到另一个版本时，  
只需要在父类容器里更新，不需要任何一个子项目的修改；如果某个子项目需要另外一个版本号时，  
只需要在dependencies中声明一个版本号即可。子类就会使用子类声明的版本号，不继承于父类版本号。
  
