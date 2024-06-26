package com.nfyc.lcnotificationservice.functions;

import com.nfyc.lcnotificationservice.domain.NfycLcResponse;
import com.nfyc.lcnotificationservice.domain.NfycLcUser;
import com.nfyc.lcnotificationservice.service.NfycLcEmailAlertService;
import com.nfyc.lcnotificationservice.service.NfycLeetcodeService;
import com.nfyc.lcnotificationservice.service.NfycRevisionEmailAlertService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.function.Supplier;

@Slf4j
@Service
public class NfycLcFunctions {

    @Autowired
    private NfycLeetcodeService nfycLeetcodeService;

    @Autowired
    private NfycLcEmailAlertService nfycLcEmailAlertService;

    @Autowired
    private NfycRevisionEmailAlertService nfycRevisionEmailAlertService;

    @Bean(name = "registerUserForEmailAlert")
    public Function<NfycLcUser, NfycLcResponse> registerUserForEmailAlert() {
        return newUser -> nfycLeetcodeService.saveLcUser(newUser);
    }
    @Bean("triggerLcEmailAlerts")
    public Supplier<String> triggerLcEmailAlerts() {
        return () -> nfycLcEmailAlertService.triggerLcEmailAlertsForUsers();
    }
    @Bean("triggerUserACSubmissionUpdates")
    public Supplier<String> triggerUserACSubmissionUpdates() {
        return () -> nfycLeetcodeService.fetchLatestACQuestionsForUsers();
    }

    @Bean("triggerRevisionLcEmailAlerts")
    public Supplier<String> triggerRevisionLcEmailAlerts() {
        return () -> nfycRevisionEmailAlertService.triggerRevisionEmailAlert();
    }
}
