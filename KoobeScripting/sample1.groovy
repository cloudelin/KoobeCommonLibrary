/**
 * sample1.groovy - Koobe Scripting Project
 *
 * Illustrate how to access data with KoobeCommonData using scripts
 * 
 * Usages:
 * groovy sample1.groovy
 *
 * Required System Environment properties:
 * JDBC_CONNECTION_STRING
 */

// Enable Koobe Maven Repository, Load Required Koobe Common Library
@GrabResolver(name='koobe', root='https://s3.amazonaws.com/koobe-repo/maven/private')
@Grab('koobe:KoobeCommonCore:0.1')
@Grab('koobe:KoobeCommonData:0.1')

// Get Koobe Application Context
def app = com.koobe.common.core.KoobeApplication.instance

// Read config from gradle.properties (e.g. JDBC_CONNECTION_STRING)
def gradleConf = new File('../gradle.properties')
if (gradleConf.isFile()) {
    app.config.applyGradlePropertyConfig(gradleConf)
}

// Koobe Data Service
def service = app.getService('koobeDataService')

// Book Data Service
def bds = service.getDataService('bookDataService')

// Retrieve book infomation
println bds.getBookName('001f53a8-d023-4d8a-a000-fec958090329')

def book = bds.getBook('001f53a8-d023-4d8a-a000-fec958090329')


println book.name
