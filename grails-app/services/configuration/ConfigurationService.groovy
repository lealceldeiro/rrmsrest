package configuration

import grails.transaction.Transactional

@Transactional
class ConfigurationService {

    def isThereAnyConfiguration(){
        def list = BConfiguration.createCriteria().list(['offset': 0]){
            or{
                eq("defaultAdminUnSetupConfigured", true)
                eq("defaultAdminUnSetupConfigured", false)
            }
        }
        list.totalCount
    }

    def isDefaultAdminUnSetup(){
        BConfiguration.countByDefaultAdminUnSetup(true) == 1
    }

    def isDefaultAdminUnSetupChanged(){
        BConfiguration.countByDefaultAdminUnSetupConfigured(true) == 1
    }

    def isDefaultUserCreated(){
        BConfiguration.countByDefaultUserCreated(true) == 1
    }

    def unSetupDefaultAdmin() {
        String []  f = ["defaultAdminUnSetup", "defaultAdminUnSetupConfigured"]
        setField(f, [true, true])
    }

    def createDefaultConfig(){
        new BConfiguration(defaultAdminUnSetupConfigured: false, defaultAdminUnSetup: false).save(flush: true, failOnError: true)
    }

    def setDefaultUserCreated(){
        String []  f = ["defaultUserCreated"]
        setField(f, [true])
    }

    private static setField(String [] fields, Object[] values){
        def l = BConfiguration.list()
        if(l.size() > 0){
            BConfiguration b = l.get(0) //there is just one row
            fields.eachWithIndex {it, idx ->
                b[it] = values[idx]
            }
            b.save(flush: true, failOnError: true)
            return b
        }
    }
}
