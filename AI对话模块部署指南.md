# AI对话模块部署指南

## 模块概述

AI对话模块已成功创建并集成到医患沟通训练系统中。该模块提供了完整的AI对话功能，支持B端（学生）与S端（模拟患者）之间的智能对话。

## 已完成的功能

### ✅ 核心功能
- **AI对话服务**: 支持多种大模型API（OpenAI、Azure等）
- **场景支持**: 术前、术中、术后三种医疗场景
- **可配置提示词**: 支持自定义和默认系统提示词
- **RESTful API**: 完整的HTTP接口供其他模块调用

### ✅ 技术特性
- **模块化设计**: 独立的ruoyi-ai-chat模块
- **配置管理**: 支持环境变量和配置文件
- **错误处理**: 完善的异常处理和错误响应
- **API文档**: 集成Swagger文档
- **单元测试**: 基础测试用例

### ✅ 文件结构
```
ruoyi-ai-chat/
├── src/main/java/com/ruoyi/aichat/
│   ├── config/                 # 配置类
│   │   ├── AiChatConfig.java
│   │   └── AiChatProperties.java
│   ├── controller/             # API控制器
│   │   └── AiChatController.java
│   ├── dto/                    # 数据传输对象
│   │   ├── AiChatRequest.java
│   │   ├── AiChatResponse.java
│   │   └── ScenarioType.java
│   ├── service/                # 服务层
│   │   ├── IAiChatService.java
│   │   └── impl/AiChatServiceImpl.java
│   └── example/                # 使用示例
│       └── AiChatExample.java
├── src/main/resources/
│   └── application-ai-chat.yml # 配置文件示例
├── src/test/java/
│   └── AiChatModuleTest.java   # 单元测试
├── pom.xml                     # Maven配置
└── README.md                   # 模块文档
```

## 部署步骤

### 1. 配置AI服务

在主应用的 `application.yml` 中添加AI配置：

```yaml
ai:
  chat:
    enabled: true
    provider: openai
    api-key: ${AI_API_KEY:your-api-key-here}
    base-url: https://api.openai.com/v1
    model: gpt-3.5-turbo
    max-tokens: 1000
    temperature: 0.7
    timeout: 30
```

### 2. 设置环境变量

```bash
# Windows
set AI_API_KEY=your-actual-api-key

# Linux/Mac
export AI_API_KEY=your-actual-api-key
```

### 3. 编译项目

```bash
mvn clean compile
```

### 4. 启动应用

启动Spring Boot应用，AI对话模块将自动加载。

## API接口使用

### 基础对话接口

**POST** `/ai/chat/send`

```bash
curl -X POST http://localhost:8080/ai/chat/send \
  -H "Content-Type: application/json" \
  -d '{
    "userMessage": "医生，我明天要做腰穿手术，很紧张怎么办？",
    "scenarioType": "PRE_OPERATION"
  }'
```

### 健康检查接口

**GET** `/ai/chat/health`

```bash
curl http://localhost:8080/ai/chat/health
```

### 获取场景类型

**GET** `/ai/chat/scenarios`

```bash
curl http://localhost:8080/ai/chat/scenarios
```

## 在其他模块中使用

### Java代码调用

```java
@Autowired
private IAiChatService aiChatService;

public void handlePatientQuestion(String question, String scenario) {
    AiChatRequest request = new AiChatRequest();
    request.setUserMessage(question);
    request.setScenarioType(scenario);
    
    AiChatResponse response = aiChatService.chat(request);
    
    if (response.getSuccess()) {
        // 处理AI回复
        String aiReply = response.getAiMessage();
        // ... 业务逻辑
    } else {
        // 处理错误
        String error = response.getErrorMessage();
        // ... 错误处理
    }
}
```

### HTTP客户端调用

```java
@Service
public class ExternalAiChatClient {
    
    @Autowired
    private RestTemplate restTemplate;
    
    public String chatWithAi(String message, String scenario) {
        String url = "http://localhost:8080/ai/chat/send";
        
        Map<String, Object> request = new HashMap<>();
        request.put("userMessage", message);
        request.put("scenarioType", scenario);
        
        ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);
        
        if (response.getStatusCode().is2xxSuccessful()) {
            Map<String, Object> data = (Map<String, Object>) response.getBody().get("data");
            return (String) data.get("aiMessage");
        }
        
        return "AI服务暂时不可用";
    }
}
```

## 配置说明

### 支持的AI服务提供商

- **OpenAI**: 默认配置，支持GPT系列模型
- **Azure OpenAI**: 修改base-url为Azure端点
- **其他**: 可扩展支持百度、阿里云等

### 场景类型

| 代码 | 描述 | 用途 |
|------|------|------|
| PRE_OPERATION | 术前 | 手术前的患者咨询和心理疏导 |
| DURING_OPERATION | 术中 | 手术过程中的患者沟通 |
| POST_OPERATION | 术后 | 术后护理指导和注意事项 |

### 性能调优

- **max-tokens**: 控制回复长度，建议500-1500
- **temperature**: 控制回复随机性，医疗场景建议0.3-0.7
- **timeout**: 根据网络情况调整，建议30-60秒

## 监控和维护

### 日志监控

```bash
# 查看AI对话相关日志
tail -f logs/ruoyi.log | grep AiChat
```

### 健康检查

定期调用健康检查接口确保服务正常：

```bash
curl http://localhost:8080/ai/chat/health
```

### 错误处理

常见错误及解决方案：

1. **API密钥错误**: 检查环境变量设置
2. **网络超时**: 增加timeout配置
3. **模型不存在**: 确认model配置正确
4. **配额超限**: 检查API使用量

## 扩展开发

### 添加新的AI服务提供商

1. 扩展 `AiChatServiceImpl` 类
2. 添加新的配置属性
3. 实现对应的API调用逻辑

### 添加新的场景类型

1. 在 `ScenarioType` 枚举中添加新类型
2. 在配置文件中添加对应的默认提示词
3. 更新API文档

### 实现对话历史

1. 添加会话存储机制
2. 扩展请求/响应结构
3. 实现上下文管理

## 安全注意事项

1. **API密钥保护**: 使用环境变量，不要硬编码
2. **访问控制**: 添加适当的权限验证
3. **数据脱敏**: 避免记录敏感的患者信息
4. **费用控制**: 监控API调用次数和成本

## 技术支持

如有问题，请：
1. 查看模块README文档
2. 检查日志文件
3. 验证配置设置
4. 联系开发团队

---

**部署完成后，AI对话模块即可为医患沟通训练系统提供智能对话能力！**
