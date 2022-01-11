package emsi.mbds

import grails.gorm.transactions.Transactional
import org.springframework.web.multipart.MultipartFile

@Transactional
class UploadIlusService {
    //path
    final String PATHILUS = "/home/hatim/Workspace/GrailsWorkspace/projet_Hatim/LeCoinCoinApp/grails-app/assets/images/";

    //writing
    Boolean addFile(MultipartFile file , Illustration ilus) {
        File target = new File(PATHILUS+""+file.getOriginalFilename())
        //System.out.println(target.getAbsolutePath())
        try {
            //writing the file into dir
            file.transferTo(target)
            //GORM
            saveORMIlus(file.getOriginalFilename(),ilus)
            return true
        }catch(IOException e){
            System.out.println("Couldnt read file"+e.getMessage())
            return false;
        }
    }

    //saving GORM
    void saveORMIlus(String filename,Illustration ilus){
        ilus.setFilename(filename)
    }
}
