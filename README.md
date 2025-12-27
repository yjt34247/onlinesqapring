# 在线问答平台 - Online Q&A Platform

基于Spring Boot的在线问答讨论系统，实现了用户注册登录、验证码验证、话题讨论和回复等功能。

## 🚀 功能特性

### ✅ 用户管理
- 用户注册（用户名、密码、年龄）
- 用户登录（验证码验证）
- 安全退出

### ✅ 验证码系统
- 纯Java实现验证码生成（无第三方库依赖）
- 4位字母数字混合验证码
- 干扰线和噪点增强安全性
- 点击图片刷新验证码

### ✅ 讨论功能
- 创建新话题（多行文本支持）
- 查看所有话题列表（按时间倒序）
- 话题详情查看
- 发表回复（支持多行文本）
- 实时显示回复内容

### ✅ 技术特性
- Spring Boot 3.5.9 + Thymeleaf
- 内存存储（ConcurrentHashMap）
- MVC架构 + IOC依赖注入
- 响应式前端设计
- 支持本地和服务器部署

## 🏗️ 系统架构

```
src/main/java/com/example/onlineqasping/
├── controller/           # 控制器层
│   ├── AuthController.java      # 认证相关
│   ├── CaptchaController.java   # 验证码生成
│   └── DiscussionController.java # 讨论相关
├── entity/              # 实体类
│   ├── User.java                # 用户实体
│   ├── Thread.java              # 话题实体
│   └── Reply.java               # 回复实体
├── service/             # 业务逻辑层
│   ├── AuthService.java        # 认证服务
│   └── DiscussionService.java  # 讨论服务
└── OnlineqaspingApplication.java # 应用主类
```

## 📦 技术栈

- **后端框架**: Spring Boot 3.5.9
- **模板引擎**: Thymeleaf 3.1.3
- **前端技术**: HTML5 + CSS3 + JavaScript
- **数据存储**: 内存存储（ConcurrentHashMap）
- **构建工具**: Maven
- **Java版本**: JDK 17

## 🛠️ 快速开始

### 环境要求
- JDK 17+
- Maven 3.6+
- Spring Boot 3.5.9


### 默认账号
- 用户名: `admin`
- 密码: `123456`
- 用户名: `user1`
- 密码: `123456`

### 验证码路径适配
系统已自动适配本地和服务器部署的路径差异，验证码功能在两种环境下均可正常工作。

## 📱 页面展示

### 1. 登录/注册页面
- 集成登录和注册功能
- 实时验证码验证
- 表单验证提示

### 2. 讨论列表页面
- 所有话题列表展示
- 按创建时间倒序排列
- 话题预览（自动截断长内容）
- 创建新话题入口

### 3. 话题详情页面
- 完整话题内容展示
- 多行文本正常换行显示
- 回复列表展示
- 发表回复表单
- 自动刷新功能（20秒间隔）

### 4. 创建话题页面
- 简洁的表单设计
- 多行文本编辑器
- 实时内容预览

## 🔒 安全特性

1. **验证码保护**
   - 自定义验证码生成算法
   - 防止暴力破解
   - 会话绑定验证

2. **会话管理**
   - HttpSession管理用户状态
   - 登录状态验证
   - 安全退出机制

3. **数据安全**
   - 密码字段序列化忽略
   - 内存数据隔离
   - 防止SQL注入（无数据库）

## 🎨 前端特性

- **响应式设计**: 适配各种屏幕尺寸
- **现代化UI**: 卡片式设计，阴影和渐变效果
- **交互体验**: 平滑过渡和动画效果
- **表单验证**: 客户端基本验证
- **自动适配**: 本地和服务器路径自动识别

## 📊 数据模型

### User（用户）
```java
id: String       // 用户ID (U1, U2...)
username: String // 用户名
password: String // 密码（序列化时忽略）
age: int         // 年龄
```

### Thread（话题）
```java
id: String        // 话题ID (T1, T2...)
title: String     // 标题
content: String   // 内容（支持多行）
author: String    // 作者用户名
createTime: Date  // 创建时间
replies: List<Reply> // 回复列表
```

### Reply（回复）
```java
id: String       // 回复ID (R1, R2...)
content: String  // 回复内容
author: String   // 回复者用户名
replyTime: Date  // 回复时间
threadId: String // 所属话题ID
```

## 🔧 配置文件

### application.properties
```properties
server.port=8080
spring.application.name=onlineqasping
spring.thymeleaf.cache=false
spring.web.resources.static-locations=classpath:/static/
```

## 🧪 测试功能

### 手动测试流程
1. 访问登录页面 → 注册新用户
2. 使用新用户登录 → 验证验证码功能
3. 创建新话题 → 验证多行文本支持
4. 查看话题列表 → 验证时间排序
5. 进入话题详情 → 发表回复
6. 刷新页面 → 验证回复显示
7. 退出登录 → 重新登录验证

## 📝 项目特点

### 教育价值
1. **完整的MVC示例**: Controller-Service-Entity分层清晰
2. **IOC实践**: Spring依赖注入完整示例
3. **无第三方验证码**: 纯Java实现验证码生成
4. **内存存储方案**: 适合小型应用的存储选择

### 工程规范
1. **代码分层**: 清晰的包结构和职责分离
2. **异常处理**: 统一的错误页面和提示
3. **日志记录**: 关键操作日志记录
4. **配置管理**: 环境无关的配置设计

---

**⭐ 如果这个项目对你有帮助，请给个 Star 支持！**
