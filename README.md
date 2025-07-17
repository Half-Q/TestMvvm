app/
├── src/
│   ├── main/
│   │   ├── java/com/half/test/
│   │   │   ├── data/
│   │   │   │   ├── local/          # 本地数据源 (Room)
│   │   │   │   │   ├── dao/
│   │   │   │   │   │   └── ClickRecordDao.kt
│   │   │   │   │   ├── entity/
│   │   │   │   │   │   └── ClickRecordEntity.kt
│   │   │   │   │   └── AppDatabase.kt
│   │   │   │   ├── remote/         # 远程数据源 (Retrofit)
│   │   │   │   │   ├── api/
│   │   │   │   │   │   └── ClickApiService.kt
│   │   │   │   │   ├── dto/
│   │   │   │   │   │   └── ClickRecordDto.kt
│   │   │   │   │   ├── source/
│   │   │   │   │   │   └── RemoteDataSource.kt
│   │   │   │   │   └── RetrofitClient.kt
│   │   │   │   ├── model/          # 数据模型
│   │   │   │   │   └── ClickRecord.kt
│   │   │   │   └── repository/     # 仓库层
│   │   │   │       └── ClickRepository.kt
│   │   │   ├── di/                 # 依赖注入
│   │   │   │   └── AppModule.kt
│   │   │   ├── util/               # 工具类
│   │   │   │   ├── DateTimeUtil.kt
│   │   │   │   └── NetworkStatus.kt
│   │   │   ├── ui/                 # UI层
│   │   │   │   ├── state/          # UI状态管理
│   │   │   │   │   └── UiState.kt
│   │   │   │   ├── viewmodel/      # ViewModel
│   │   │   │   │   └── ClickViewModel.kt
│   │   │   │   └── activity/       # Activity
│   │   │   │       └── MainActivity.kt
│   │   │   └── App.kt              # Application类
│   │   ├── res/
│   │   │   └── layout/
│   │   │       └── activity_main.xml
│   │   └── AndroidManifest.xml