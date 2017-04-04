package box.white.utsusemi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import box.white.utsusemi.model.UtsusemiConfigModel;
import box.white.utsusemi.service.DbAnalyzeService;

/**
 * @author seri
 *
 */
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        try {
            // initialize
            BootStrap bs = new BootStrap();
            bs.init(args);

            UtsusemiConfigModel config = new UtsusemiConfigModel();

            mainProcess(config);

        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
        }
    }

    protected static void mainProcess(UtsusemiConfigModel config) {

        DbAnalyzeService db = new DbAnalyzeService(config);

    }
}
