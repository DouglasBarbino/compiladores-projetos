const int tempo = 1000;
const int pinoPot = A4;

float y;
String mensagem;

void setup()
{	
	pinMode(pinoPot, INPUT);
	Serial.begin(115200);
}

void loop()
{

	if (Serial.avaiable() > 0)
	{
		mensagem = Serial.read();
		if (x == "sim")
		{
			y = analogRead(pinoPot);
			mensagem = "Potencia dada é " + y;
			Serial.println(mensagem);
			delay(tempo);
		}
		else
			Serial.printl("esperando digitar 'sim' para fazer a leitura");
	}

}

