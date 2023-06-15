package com.challenge.challenge.service.impl;

import com.challenge.challenge.entities.Login;
import com.challenge.challenge.entities.UserData;
import com.challenge.challenge.repository.ILoginRepository;
import com.challenge.challenge.repository.ISeguridadLogin;
import com.challenge.challenge.service.IHackerDetector;
import com.challenge.challenge.service.ILogin;
import com.challenge.challenge.utilities.enums.Accion;
import com.challenge.challenge.utilities.funciones.FuncionesComunes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LoginImpl implements ILogin {

    @Autowired
    ILoginRepository loginRepository;

    @Autowired
    ISeguridadLogin seguridadLogin;

    @Autowired
    IHackerDetector hackerDetectorService;

    @Override
    public String login(UserData user) {
        if(loginRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword()).size()>0){
            String token =  UUID.randomUUID().toString();
            String ip = hackerDetectorService.parseLine();
            Login login = new Login();
            if(!ip.isEmpty() || ip != null){
                login.setIp(ip);
                login.setAccion(Accion.SIGNIN_SUCCESS.getAccion());
                login.setUsername(user.getUsername());
                login.setToken(token);
                login.setFechaCreacion(FuncionesComunes.obtenerFechaEpoch());
                seguridadLogin.save(login);
            }else{
                login.setIp(ip);
                login.setAccion(Accion.SIGNIN_FAILURE.getAccion());
                login.setUsername(user.getUsername());
                login.setToken("NULL");
                login.setFechaCreacion(FuncionesComunes.obtenerFechaEpoch());
                seguridadLogin.save(login);
            }

            return token;
        }
        //response.put("error", "Not Found");
        return null;
    }
//select * from usuario;
//select * from login;


}
