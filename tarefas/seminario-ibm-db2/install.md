# Configurando e Executando o Db2 Docker

### 1. Crie um novo diretório para a imagem do Docker:

```bash
mkdir Docker
```

### 2. Acesse o diretório:

```bash
cd Docker
```

### 3. Efetue login no contêiner do Docker:

```bash
sudo docker login
```

### 4. Puxe a imagem Db2 Docker do ICR:

```bash
docker pull icr.io/db2_community/db2
```

### 5. Crie um arquivo de variáveis de ambiente, .env_list, para sua imagem Db2 Community Edition:

```bash
vi .env_list
```

### 6. Cole o seguinte conteúdo no arquivo de variáveis de ambiente:

```bash
LICENSE=accept
DB2INSTANCE=db2inst1
DB2INST1_PASSWORD=password
DBNAME=testdb
BLU=false
ENABLE_ORACLE_COMPATIBILITY=false
UPDATEAVAIL=NO
TO_CREATE_SAMPLEDB=false
REPODB=false
IS_OSXFS=false
PERSISTENT_HOME=true
HADR_ENABLED=false
ETCD_ENDPOINT=
ETCD_USERNAME=
ETCD_PASSWORD=
```

#### Explicação das variáveis:

<ul>
    <li><b>LICENSE</b>: Aceita os termos e condições do software Db2 contidos nesta imagem.</li>
    <li><b>DB2INSTANCE</b>: Nome da instância Db2.</li>
    <li><b>DB2INST1_PASSWORD</b>: Senha da instância Db2.</li>
    <li><b>DBNAME</b>: Cria um banco de dados inicial com o nome fornecido (deixe vazio caso não seja necessário).</li>
    <li><b>BLU</b>: Define se a aceleração BLU está habilitada (true) ou desativada (false).</li>
    <li><b>ENABLE_ORACLE_COMPATIBILITY</b>: Configura a compatibilidade do Oracle na instância como ativada (true) ou desativada (false).</li>
    <li><b>UPDATEAVAIL</b>: Configura como YES se houver uma instância existente executando um novo container com um nível superior Db2.</li>
    <li><b>TO_CREATE_SAMPLEDB</b>: Cria um banco de dados de amostra (true).</li>
    <li><b>REPODB</b>: Cria um banco de dados de repositório do Data Server Manager (true).</li>
    <li><b>IS_OSXFS</b>: Identifica o sistema operacional como macOS (true).</li>
    <li><b>PERSISTENT_HOME</b>: Configurado como true por padrão, deve ser false somente ao executar o Docker no Windows.</li>
    <li><b>HADR_ENABLED</b>: Configura Db2 HADR para a instância (true).</li>
</ul>

### 7. Salve o arquivo, pressionando ESCAPE (ESC) e inserindo:

```bash
:wq!
```

### 8. Insira e execute o comando a seguir para entrar no contêiner do Docker:

```bash
docker run -h db2server --name db2server --restart=always --detach --privileged=true -p 50000:50000 --env-file .env_list -v /Docker:/database icr.io/db2_community/db2
```

<ul>
    <li><b>-h</b>: Designa o nome db2server para o contêiner Docker.</li>
    <li><b>-p</b>: Especifica os números de porta a utilizar.</li>
    <li><b>--privileged</b>: Inicia o container em modo privilegiado.</li>
    <li><b>-v</b>: Define o volume usado para a imagem Db2 Docker.</li>
</ul>

### 9. Acesse a instância em execução Db2 dentro do contêiner Docker:

```bash
docker exec -ti db2server bash -c "su - db2inst1"
```

<b>db2inst1</b>: Valor associado à variável DB2INSTANCE no arquivo .env_list.

Agora você pode criar um banco de dados Db2 dentro da instância ativa.