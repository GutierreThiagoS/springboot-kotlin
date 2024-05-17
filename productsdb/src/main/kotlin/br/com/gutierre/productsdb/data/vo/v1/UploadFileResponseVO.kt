package br.com.gutierre.productsdb.data.vo.v1

data class UploadFileResponseVO(
    var fileName: String = "",
    var fileDownloadUri: String = "",
    var fileType: String = "",
    var fileSize: Long = 0
)