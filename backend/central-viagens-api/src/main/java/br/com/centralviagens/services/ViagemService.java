package br.com.centralviagens.services;

import br.com.centralviagens.dtos.request.ViagemRequestDTO;
import br.com.centralviagens.dtos.response.ViagemResponseDTO;
import br.com.centralviagens.models.Viagem;
import br.com.centralviagens.repositories.ViagemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViagemService {

    @Autowired
    private ViagemRepository viagemRepository;

    @Transactional
    public ViagemResponseDTO registerTrip(ViagemRequestDTO requestDTO) {
        Viagem viagem = new Viagem();
        viagem.setOrigem(requestDTO.getOrigem());
        viagem.setDestino(requestDTO.getDestino());
        viagem.setDataPartida(requestDTO.getDataPartida());
        viagem.setPreco(requestDTO.getPreco());
        viagem.setBagagemGrande(requestDTO.isBagagemGrande());
        viagem.setAnimaisEstimação(requestDTO.isAnimaisEstimacao());
        viagem.setObservacoes(requestDTO.getObservacoes());
        viagem.setVeiculo(requestDTO.getVeiculo());
        viagem = viagemRepository.save(viagem);

        ViagemResponseDTO responseDTO = new ViagemResponseDTO(
                viagem.getId(),
                viagem.getOrigem(),
                viagem.getDestino(),
                viagem.getDataPartida(),
                viagem.getPreco(),
                viagem.getVeiculo().getMarca() + " - " + viagem.getVeiculo().getModelo()
        );

        return responseDTO;
    }

}
