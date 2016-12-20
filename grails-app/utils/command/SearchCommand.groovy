package command

import grails.validation.Validateable

/**
 * Created by Asiel on 12/17/2016.
 */
@Validateable
class SearchCommand {
    String criteria

    static constraints = {
        criteria nullable: true, blank: false
    }
}
