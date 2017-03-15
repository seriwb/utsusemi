package box.white.utsusemi

import spock.lang.Specification

class MainSpec extends Specification {
    def "execute main"() {
        setup:
        def result = null

        when:
        try {
            Main.main()
        } catch (e) {
            result = e
        }

        then:
        result == null
    }
}
