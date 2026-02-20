# AndroidGlass

一个最基础的 Android App（Kotlin + XML）示例仓库，已配置 GitHub Actions 自动构建 APK。

## 项目说明

- 应用包名：`com.sxwzxc.androidglass`
- 入口页面：`MainActivity`
- 页面内容：居中显示 `Hello AndroidGlass!`

## 本地构建 APK

```bash
./gradlew assembleDebug
```

构建成功后，APK 在：

`app/build/outputs/apk/debug/app-debug.apk`

## GitHub 自动化构建（已配置）

已新增工作流：`.github/workflows/build-apk.yml`

触发方式：
- 任意分支 push
- pull request

工作流会自动：
1. 安装 JDK 17
2. 安装 Android SDK
3. 执行 `./gradlew assembleDebug`
4. 上传 APK 构建产物（artifact 名称：`app-debug-apk`）

## 需要你手动确认的点

1. 确认仓库已启用 GitHub Actions（通常默认启用）。
2. 每次工作流完成后，在对应的 Actions Run 页面下载 artifact：`app-debug-apk`。
3. 下载后把 APK 安装到安卓手机（需允许“安装未知来源应用”）。
