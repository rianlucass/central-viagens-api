package br.com.centralviagens.services;

import br.com.centralviagens.dtos.request.VeiculoRequestDTO;
import br.com.centralviagens.dtos.response.VeiculoResponseDTO;
import br.com.centralviagens.models.Veiculo;
import br.com.centralviagens.repositories.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    public VeiculoResponseDTO registerCar(VeiculoRequestDTO requestDTO) {
        Veiculo veiculo = new Veiculo();
        veiculo.setModelo(requestDTO.getModelo());
        veiculo.setTipo(requestDTO.getTipo());
        veiculo.setMarca(requestDTO.getMarca());
        veiculo.setPlaca(requestDTO.getPlaca());
        veiculo.setAno(requestDTO.getAno());
        veiculo.setCapacidade(requestDTO.getCapacidade());
        veiculo = veiculoRepository.save(veiculo);

        VeiculoResponseDTO responseDTO = new VeiculoResponseDTO(
                veiculo.getId(),
                veiculo.getModelo(),
                veiculo.getTipo(),
                veiculo.getMarca(),
                veiculo.getPlaca(),
                veiculo.getAno(),
                veiculo.getCapacidade()
        );

        return responseDTO;
    }

}
