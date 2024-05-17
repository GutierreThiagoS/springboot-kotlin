package br.com.gutierre.productsdb.controllers

import br.com.gutierre.productsdb.data.vo.v1.UploadFileResponseVO
import br.com.gutierre.productsdb.services.FileStorageService
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.logging.Logger

@Tag(
    name = "File Endpoint"
)
@RestController
@RequestMapping("/api/file/v1")
class FileController {

    private val logger = Logger.getLogger(FileController::class.java.name)

    @Autowired
    private lateinit var fileStorageService: FileStorageService

    @PostMapping("/uploadFile")
    fun uploadFile(@RequestParam("file") file: MultipartFile): UploadFileResponseVO {

        val fileName = fileStorageService.storeFile(file)

        val fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/api/file/uploadFile/")
            .path(fileName)
            .toUriString()
        return UploadFileResponseVO(
            fileName = fileName,
            fileDownloadUri = fileDownloadUri,
            fileType = file.contentType!!,
            fileSize = file.size
        )
    }

    @PostMapping("/uploadMultipleFile")
    fun uploadMultipleFile(@RequestParam("files") files: Array<MultipartFile>): List<UploadFileResponseVO> {

        val uploadFileResponseVOs = arrayListOf<UploadFileResponseVO>()

        for (file in files) {
            val uploadFileResponseVO: UploadFileResponseVO = uploadFile(file)
            uploadFileResponseVOs.add(uploadFileResponseVO)
        }

        return uploadFileResponseVOs
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    fun downloadFile(@PathVariable fileName: String, request: HttpServletRequest): ResponseEntity<Resource> {

        val resource = fileStorageService.loadFileAsResource(fileName)

       var contentType = ""

        try {
            contentType = request.servletContext.getMimeType(resource.file.absolutePath)
        } catch (e: Exception) {
            logger.info("Coun not determine file type")
        }

        if (contentType.isBlank()) contentType = "application/octet-stream"

        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(contentType))
            .header(HttpHeaders.CONTENT_DISPOSITION, """attachment; filename="${resource.filename}" """)
            .body(resource)
    }

}