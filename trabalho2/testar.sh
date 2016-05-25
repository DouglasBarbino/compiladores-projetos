if [ $1 = 'sintatico' ] ;
then

        for i in {1..7}
        do
                java -jar trabalho3.jar $2casosDeTeste/comErrosLexicosSintaticos/entrada/exemplo$i.faz $2casosDeTeste/resultados/erro-ls-exemplo$i.txt
        done
fi

if [ $1 = 'gerador' ] ;
then

        for i in {1..6}
        do
                java -jar trabalho3.jar $2casosDeTeste/semErros/entrada/exemplo$i.faz $2casosDeTeste/resultados/exemplo$i.ino
        done
fi


if [ $1 = 'semantico' ] ;
then

        for i in {1..8}
        do
                java -jar trabalho3.jar $2casosDeTeste/comErrosSemanticos/entrada/exemplo$i.faz $2casosDeTeste/resultados/erro-as-exemplo$i.txt
        done

fi



if [ $1 = 'tudo' ] ;
then
	for i in {1..6}
	do
	        java -jar trabalho3.jar $2casosDeTeste/semErros/entrada/exemplo$i.faz $2casosDeTeste/resultados/exemplo$i.ino
	done

        for i in {1..7}
        do
                java -jar trabalho3.jar $2casosDeTeste/comErrosSemanticos/entrada/exemplo$i.faz $2casosDeTeste/resultados/erro-as-exemplo$i.txt
        done

        for i in {1..7}
        do
                java -jar trabalho3.jar $2casosDeTeste/comErrosLexicosSintaticos/entrada/exemplo$i.faz $2casosDeTeste/resultados/erro-ls-exemplo$i.txt
        done

fi


