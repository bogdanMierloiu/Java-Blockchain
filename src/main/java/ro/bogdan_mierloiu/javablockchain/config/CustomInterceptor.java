package ro.bogdan_mierloiu.javablockchain.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import ro.bogdan_mierloiu.javablockchain.exception.InvalidBlockChainException;
import ro.bogdan_mierloiu.javablockchain.service.BlockChainService;
import ro.bogdan_mierloiu.javablockchain.util.Miner;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomInterceptor implements HandlerInterceptor {

    private final Miner miner;
    private final BlockChainService blockChainService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!request.getRequestURI().equals("/blockchain") && !miner.isValidChain(blockChainService.getBlockChain()))
            throw new InvalidBlockChainException("Block chain is not valid. The data was compromised.");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("Request has been processed");
    }

}
