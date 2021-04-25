package example.services;

import example.dao.LambdaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LambdaService {

    private LambdaDao lambdaDao;

    public LambdaService() {
    }

    public LambdaService(LambdaDao lambdaDao) {
        this.lambdaDao = lambdaDao;
    }

    public void deleteAllData() {
        this.lambdaDao.deleteAllData();
    }

    public LambdaDao getLambdaDao() {
        return lambdaDao;
    }

    @Autowired
    public void setLambdaDao(LambdaDao lambdaDao) {
        this.lambdaDao = lambdaDao;
    }
}
