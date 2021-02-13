#   Spring与SpringMVC环境配置



- 依赖spring-webmvc
- 配置DispatcherServlet
- 启动Spring MVC注解模式
- 配置请求与响应字符集
- 配置FreeMarker模板引擎
- 配置Json序列化组件



### 1. Idea配置Maven web工程

1. 新建项目
- 选择maven
- Name
- Location
- Artifact Coordinates 工件坐标系
  - GroupId
  - ArtifactId
  - Version

2. File->Project Structure->Modules->点+->选择 Web，在右侧进行配置
   - 配置 Deployment Descriptors 的path（Web.xml路径）：
     - 点击Path旁边的修改icon. 配置路径为 projectName/src/main/webapp/WEB-INF/web.xml
     - Deployment descriptor version配置为3.1
   - 配置Web Resource Directories
     - 配置path：配置路径为 projectName/src/main/webapp
     - Relative path in deployment directory: /
   - 点击 Create Artifact
   - 点击 Apply，OK 

3. 新建测试页面
   - 在webapp目录下创建index.html
4. 配置Tomcat
   - 点击Add Configuration->左上角点击+号，从下往上找，Tomcat Server->Local
   - Name:随便起，我起的是start Tomcat
   - Server
     - Application server: 选择Tomcat在本地电脑的路径
     - VM options:
       - On "update" action: Update classes and resources
       - On frame deactivatetion: Update classes and resources
     - Tomcat Server Setting
       - HTTP port: 8088
   - Deployment
     - 右侧+号->Artifact
     - Application context:/





### 2. pom.xml文件配置

1.配置

- 阿里云maven镜像

- 依赖spring-webmvc
- freemarker
- JSON

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:task="http://www.springframework.org/schema/task"
       xsi:schemaLocation="
        http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd
        http://www.springframework.org/schema/task
        http://www.springframework.org/schema/task/spring-task.xsd
        http://www.springframework.org/schema/mvc
        http://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <!--  3. 开启SpringMVC注解模式  -->
    <context:component-scan base-package="com.imooc"/>
    <mvc:annotation-driven>
        <!--  响应中文乱码  -->
        <mvc:message-converters>
            <bean class="org.springframework.http.converter.StringHttpMessageConverter">
                <property name="supportedMediaTypes">
                    <list>
                        <value>text/html;charset=utf-8</value>
                        <!--  6.JSON序列化输出配置  -->
                        <value>application/json;charset=utf-8</value>
                    </list>
                </property>
            </bean>
        </mvc:message-converters>
    </mvc:annotation-driven>
    <!--  排除静态资源、例如css、js  -->
    <mvc:default-servlet-handler/>


    <!--  5.配置Freemarker模板引擎  -->
    <bean id="freemarkerConfig" class="org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer">
        <property name="templateLoaderPath" value="/WEB-INF/ftl"/>
        <property name="freemarkerSettings">
            <props>
                <prop key="defaultEncoding">UTF-8</prop>
            </props>
        </property>
    </bean>
    <bean id="ViewResolover" class="org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver">
        <property name="contentType" value="text/html;charset=utf-8"/>
        <property name="suffix" value=".ftl"/>
    </bean>
</beans>
```

2.Project Structure

右侧Available Elements->全选->put into location



### 3. 配置 webapp/WEB-INF中的web.xml
配置 DispatcherServlet
```
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
         version="3.1">
    <!--  2. 配置DispatcherServlet  -->
    <servlet>
        <servlet-name>springmvc</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:applicationContext*.xml</param-value>
        </init-param>
        <load-on-startup>0</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>springmvc</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>

    <!--  4. 解决中文乱码  -->
    <filter>
        <filter-name>characterFilter</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>UTF-8</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>characterFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
</web-app>
```



### 4. 配置 src/main/resources中的applicationContext文件

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:task="http://www.springframework.org/schema/task"
       xsi:schemaLocation="
        http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd
        http://www.springframework.org/schema/task
        http://www.springframework.org/schema/task/spring-task.xsd
        http://www.springframework.org/schema/mvc
        http://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <!--  3. 开启SpringMVC注解模式  -->
    <context:component-scan base-package="com.imooc"/>
    <mvc:annotation-driven>
        <!--  响应中文乱码  -->
        <mvc:message-converters>
            <bean class="org.springframework.http.converter.StringHttpMessageConverter">
                <property name="supportedMediaTypes">
                    <list>
                        <value>text/html;charset=utf-8</value>
                        <!--  6.JSON序列化输出配置  -->
                        <value>application/json;charset=utf-8</value>
                    </list>
                </property>
            </bean>
        </mvc:message-converters>
    </mvc:annotation-driven>
    <!--  排除静态资源、例如css、js  -->
    <mvc:default-servlet-handler/>


    <!--  5.配置Freemarker模板引擎  -->
    <bean id="freemarkerConfig" class="org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer">
        <property name="templateLoaderPath" value="/WEB-INF/ftl"/>
        <property name="freemarkerSettings">
            <props>
                <prop key="defaultEncoding">UTF-8</prop>
            </props>
        </property>
    </bean>
    <bean id="ViewResolover" class="org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver">
        <property name="contentType" value="text/html;charset=utf-8"/>
        <property name="suffix" value=".ftl"/>
    </bean>
</beans>
```

### 5. Spring与MyBatis整合配置

- 配置mybatis-spring及驱动
- 配置数据源与连接池
- 配置SqlSessionFactory
- 配置Mapper扫描器
- 创建mybatis-config.xml



5.1新建数据库

- navicat新建数据库



### 6. SSM与其他组件整合
- 配置logback日志输出
- 声明式事务配置
- 整合JUnit单元测试
























