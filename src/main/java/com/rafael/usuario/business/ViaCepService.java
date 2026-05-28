package com.rafael.usuario.business;

import com.rafael.usuario.infrastructure.clients.ViaCepClient;
import com.rafael.usuario.infrastructure.clients.ViaCepDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ViaCepService {

    private final ViaCepClient client;

    public ViaCepDTO buscarDadosEndereco(String cep) {
        return client.buscarDadosEndereco(processarCep(cep));
    }

    private String processarCep(String cep) {
        String cepFormatado = cep.replace(" ", "").
                replace("-", "");

        if(!cepFormatado.matches("\\d+")
                || !Objects.equals(cepFormatado.length(), 8)) {
            throw new IllegalArgumentException("O cep contém caracteres inválidos, por favor verificar");
        }

        return cepFormatado;
    }
}
