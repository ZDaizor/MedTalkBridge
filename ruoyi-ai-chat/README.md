# AI对话模块

## 模块简介

AI对话模块是医患沟通训练系统的核心组件，提供AI驱动的对话功能。该模块支持B端（学生）与S端（模拟患者）之间的智能对话，涵盖术前、术中、术后三种场景。

## 功能特性

- ✅ 支持多种AI服务提供商（OpenAI、Azure、百度等）
- ✅ 可配置的系统提示词，支持不同医疗场景
- ✅ RESTful API接口，便于其他模块调用
- ✅ 完整的请求/响应数据结构
- ✅ 健康检查和服务监控
- ✅ 批量对话支持
- ✅ 详细的API文档（Swagger）

## 快速开始

### 1. 配置AI服务

在 `application.yml` 中添加AI配置：

```yaml
ai:
  chat:
    enabled: true
    provider: openai
    api-key: your-api-key-here
    base-url: https://api.openai.com/v1
    model: gpt-3.5-turbo
    max-tokens: 1000
    temperature: 0.7
    timeout: 30
```

### 2. 环境变量配置（推荐）

为了安全起见，建议使用环境变量配置API密钥：

```bash
export AI_API_KEY=your-actual-api-key
```

### 3. 启动应用

确保AI对话模块已被包含在主应用中，启动Spring Boot应用即可。

## API接口

### 基础对话接口

**POST** `/ai/chat/send`

请求体示例：
```json
{
  "userMessage": "医生，我明天要做腰穿手术，很紧张怎么办？",
  "scenarioType": "PRE_OPERATION",
  "systemPrompt": "你是一位经验丰富的医生...",
  "sessionId": "session_123456",
  "maxTokens": 500,
  "temperature": 0.7
}
```

响应示例：
```json
{
  "code": 200,
  "msg": "对话成功",
  "data": {
    "aiMessage": "我理解您对手术的担心，这是很正常的反应...",
    "sessionId": "session_123456",
    "scenarioType": "PRE_OPERATION",
    "timestamp": "2025-09-03T09:30:00",
    "tokensUsed": 150,
    "processingTime": 1200,
    "success": true
  }
}
```

### 其他接口

- **GET** `/ai/chat/prompt/{scenarioType}` - 获取默认系统提示词
- **GET** `/ai/chat/scenarios` - 获取所有场景类型
- **GET** `/ai/chat/health` - 健康检查
- **POST** `/ai/chat/batch` - 批量对话

## 场景类型

| 代码 | 描述 | 说明 |
|------|------|------|
| PRE_OPERATION | 术前 | 手术前的患者沟通 |
| DURING_OPERATION | 术中 | 手术过程中的患者沟通 |
| POST_OPERATION | 术后 | 手术后的患者指导 |

## 使用示例

### Java代码调用

```java
@Autowired
private IAiChatService aiChatService;

public void example() {
    AiChatRequest request = new AiChatRequest();
    request.setUserMessage("医生，手术会很痛吗？");
    request.setScenarioType("PRE_OPERATION");
    
    AiChatResponse response = aiChatService.chat(request);
    
    if (response.getSuccess()) {
        System.out.println("AI回复: " + response.getAiMessage());
    } else {
        System.out.println("对话失败: " + response.getErrorMessage());
    }
}
```

### HTTP请求示例

```bash
curl -X POST http://localhost:8080/ai/chat/send \
  -H "Content-Type: application/json" \
  -d '{
    "userMessage": "医生，我需要注意什么？",
    "scenarioType": "POST_OPERATION"
  }'
```

## 配置说明

### 完整配置示例

```yaml
ai:
  chat:
    enabled: true                    # 是否启用AI对话功能
    provider: openai                 # AI服务提供商
    api-key: ${AI_API_KEY}          # API密钥
    base-url: https://api.openai.com/v1  # API基础URL
    model: gpt-3.5-turbo            # 使用的模型
    max-tokens: 1000                # 默认最大token数
    temperature: 0.7                # 默认温度参数
    timeout: 30                     # 请求超时时间（秒）
    retry-count: 3                  # 重试次数
    prompts:                        # 默认提示词配置
      pre-operation: "..."          # 术前场景提示词
      during-operation: "..."       # 术中场景提示词
      post-operation: "..."         # 术后场景提示词
```

## 注意事项

1. **API密钥安全**: 请勿在代码中硬编码API密钥，建议使用环境变量
2. **费用控制**: 注意监控API调用次数和token使用量，避免产生意外费用
3. **错误处理**: 建议在调用时添加适当的错误处理逻辑
4. **性能优化**: 对于高频调用场景，建议实现缓存机制

## 故障排除

### 常见问题

1. **API密钥错误**: 检查配置中的API密钥是否正确
2. **网络连接问题**: 确认服务器可以访问AI服务提供商的API
3. **模型不存在**: 确认配置的模型名称是否正确
4. **超时问题**: 适当增加timeout配置值

### 日志查看

AI对话模块会输出详细的日志信息，可以通过以下方式查看：

```bash
# 查看应用日志
tail -f logs/ruoyi.log | grep AiChat
```

## 扩展开发

如需扩展AI对话功能，可以：

1. 实现新的AI服务提供商适配器
2. 添加新的场景类型
3. 扩展请求/响应数据结构
4. 实现对话历史记录功能

## 技术支持

如有问题，请联系开发团队或查看项目文档。
