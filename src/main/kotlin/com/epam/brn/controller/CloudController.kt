package com.epam.brn.controller

import com.epam.brn.constant.BrnPath
import com.epam.brn.constant.BrnPath.RESOURCES_ROOT_URL
import com.epam.brn.constant.BrnPath.UPLOAD
import com.epam.brn.service.CloudService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.apache.logging.log4j.kotlin.logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * Documentation https://github.com/Brain-up/brn/wiki/Cloud-file-resource-loading
 */
@RestController
@RequestMapping(BrnPath.CLOUD)
@Api(value = BrnPath.CLOUD, description = "Contains actions for cloud upload and bucket listing")
class CloudController(@Autowired private val cloudService: CloudService) {

    private val log = logger()

    @GetMapping(UPLOAD)
    @ApiOperation("Get upload form")
    @Throws(Exception::class)
    fun signatureForClientDirectUpload(@RequestParam fileName: String?): ResponseEntity<Map<String, Any>?> {
        try {
            return ResponseEntity.ok(cloudService.signatureForClientDirectUpload(fileName))
        } catch (exception: IllegalArgumentException) {
            log.error(exception)
            return ResponseEntity.badRequest().build()
        }
    }

    @GetMapping(RESOURCES_ROOT_URL)
    @ApiOperation("Get bucket url")
    @Throws(Exception::class)
    fun listBucket(): ResponseEntity<String> = ResponseEntity.ok(cloudService.listBucket())
}
