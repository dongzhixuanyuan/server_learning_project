package webmvc.locale

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.MessageSource
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
@Order(2)
class LocalResIntercepter:HandlerInterceptor {

    @Autowired var localResolver: org.springframework.web.servlet.LocaleResolver? = null

    @Autowired
    @Qualifier("i18n")
    var msgRs:MessageSource? = null

    override fun postHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        modelAndView: ModelAndView?
    ) {
        if (modelAndView != null) {
            val resolveLocale = localResolver!!.resolveLocale(request)
            modelAndView.addObject("stringSource",msgRs)
            modelAndView.addObject("locale",resolveLocale)
        }
    }

}