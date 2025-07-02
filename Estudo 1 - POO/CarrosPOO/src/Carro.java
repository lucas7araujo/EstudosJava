public class Carro {

    // Atributos

    String marca;
    String modelo;
    int ano;
    String placa;
    double quilometragem;

    // Construtor

    public Carro(String marca, String modelo, int ano, String placa, double quilometragem) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.placa = placa;
        this.quilometragem = quilometragem;
    }

    public void exibeCarro() {
        System.out.println("\nMarca: " + marca);
        System.out.println("Modelo: " + modelo);
        System.out.println("Ano: " + ano);
        System.out.println("Placa: " + placa);
        System.out.println("Quilômetragem: " + quilometragem);
    }

    @Override
    public String toString() {
        return "\nMarca: " + marca + "\nModelo: " + modelo + "\nAno: " + ano + "\nPlaca:" + placa + "\nQuilometragem: " + quilometragem +" KM";
    }

}
