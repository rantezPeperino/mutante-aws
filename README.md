
## Installation

El deploy en aws se puede realizar en el servicio ECS creando las 2 imagenes
Primer paso el login en ECR para subir las imagenes de springboot y mysql.
aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin xxxxxxx.dkr.ecr.us-east-1.amazonaws.com
Crean las imagenes y subirlas al servicio de ECR.

springboot: 


-Crear el repo 
```bash
aws ecr create-repository --repository-name mutante-springboot --region us-east-1
```


-Crean la imagen 
```bash
docker build -t mutante-springboot .
```
-Crear Tag 

```bash
docker tag mutante-springboot:latest xxxxxxxxx.dkr.ecr.us-east-1.amazonaws.com/mutante-springboot:latest
```

-Subir imagen 
```bash
docker push xxxxxxxxxxx.dkr.ecr.us-east-1.amazonaws.com/mutante-springboot:latest
```
-------------------------------------------------------
mysql: 

-Crear el repo 
```bash
aws ecr create-repository --repository-name mutante-mysql-aws --region us-east-1
```

-Crean la imagen 
```bash
docker build -t mutante-mysql-aws .
```

-Crear Tag 
```bash
docker tag mutante-mysql-aws:latest xxxxxxxxx.dkr.ecr.us-east-1.amazonaws.com/mutante-mysql-aws:latest
```

-Subir imagen 
```bash
docker push xxxxxxxxx.dkr.ecr.us-east-1.amazonaws.com/mutante-mysql-aws:latest
```

-------------------------------------------------------
Se deben crear los servicios para la comunicacion entre el proyecto springboot y mysql. Habilitar la url publica para poder tener acceso a los endpoint del proyecto springboot. Se encuentra el archivo en la carpeta .aws initMutante.tf

Crear una VPC 


Crear Subnets (públicas y privadas) 


Crear Internet Gateway y asociarlo a la VPC 


Crear NAT Gateway para la subnet privada 


Crear y configurar tablas de ruteo 


Crear Security Groups 


Crear un cluster ECS 


Crear Task Definitions para ambos contenedores 


Crear Application Load Balancer (ALB) 


Crear servicios ECS
