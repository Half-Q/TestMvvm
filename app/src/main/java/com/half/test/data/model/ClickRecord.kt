package com.half.test.data.model

data class ClickRecord (
    val id: Int? = null,          // 远程ID（由MySQL生成）
    val localId: Int = 0,         // 本地ID（由Room生成）
    val timestamp: String,
    val content: String,
    val isSynced: Boolean = false // 是否已同步到远程
)