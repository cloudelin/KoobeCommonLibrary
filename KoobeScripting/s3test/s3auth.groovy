@Grab('net.java.dev.jets3t:jets3t:0.9.0')
@Grab('commons-codec:commons-codec:1.8')

import org.jets3t.service.*
import org.jets3t.service.model.*
import org.jets3t.service.security.*
import org.jets3t.service.impl.rest.httpclient.*

Scanner scanner = new Scanner(System.in)

println("Access Key ID: ")
String accessKey = scanner.next()

println("Secret Access Key: ")
String secretKey = scanner.next()

println("Bucket Name for Testing (e.g. koobe-repo): ")
String bucketName = scanner.next()

AWSCredentials credentials = new AWSCredentials(accessKey, secretKey)
RestS3Service s3service = new RestS3Service(credentials)

println "List of all visible buckets: "

s3service.listAllBuckets().each { s3bucket ->
    println "* ${s3bucket.name}"
}

S3Bucket s3bucket = s3service.getBucket(bucketName)

if (s3bucket) {
    println "List all objects in ${bucketName}"

    def i = 0

    s3service.listObjects(s3bucket).each { object ->
        println "${++i}. ${bucketName}/${object.key}"
    }
}
else {
    println "Couldn't access to ${bucketName}."
}

