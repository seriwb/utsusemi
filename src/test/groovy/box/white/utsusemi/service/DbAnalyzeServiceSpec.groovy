package box.white.utsusemi.service

import box.white.utsusemi.model.UtsusemiConfigModel;
import spock.lang.Specification

class DbAnalyzeServiceSpec extends Specification {

    def "プロパティファイルの取得テスト"() {
        setup:
        UtsusemiConfigModel config = new UtsusemiConfigModel()
        def testSuite = new DbAnalyzeService(config)

        when:
        Properties prop = testSuite.getDbProperties("mysql")

        then:
        prop.getProperty("driver") == "com.mysql.jdbc.Driver"
    }

}
