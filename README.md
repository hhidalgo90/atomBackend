# TasksProject - Spring Boot Backend Application

## Descripción

Este es un backend de gestión de tareas creado utilizando **Spring Boot**. Permite la creación, actualización, obtención y eliminación de tareas para cada usuario. La información se almacena en **Firebase Firestore** y las credenciales se gestionan mediante variables de entorno para asegurar la seguridad de la aplicación.

## Tecnologías utilizadas

- **Spring Boot**: Framework principal para la creación de aplicaciones Java basadas en el patrón de diseño MVC.
- **Firebase Firestore**: Base de datos NoSQL en la nube para almacenar los datos de los usuarios y sus tareas.
- **Java 17**: Lenguaje de programación utilizado para desarrollar la aplicación.
- **Maven**: Sistema de gestión de dependencias y construcción del proyecto.
- **Google Cloud**: Se utiliza para el despliegue en la nube y la integración con Firebase.
- **Jib Maven Plugin**: Plugin de Maven para crear imágenes de contenedor y desplegar la aplicación en Google Cloud Run.

## Decisiones de Diseño

### Estructura de la Aplicación

La aplicación sigue una arquitectura **MVC** (Modelo-Vista-Controlador) estándar, pero con la lógica de negocio encapsulada en los servicios, sin una interfaz de usuario directa (servidor de API RESTful).

- **Servicios**: Contienen la lógica de negocio relacionada con las tareas, como agregar, eliminar, actualizar y obtener las tareas.
- **Controladores**: Los controladores son responsables de gestionar las solicitudes HTTP entrantes y delegar la lógica de negocio a los servicios correspondientes.
- **Repositorios**: Interacción con Firebase Firestore. La conexión a la base de datos se realiza de forma programática utilizando el SDK de Firebase.
- **Configuración**: Firebase se configura al inicio de la aplicación usando un archivo JSON de credenciales que se gestiona mediante variables de entorno para mantener la seguridad de las claves.

### Seguridad

Para proteger las credenciales de Firebase, las variables de entorno se configuran para ser leídas en el momento del arranque de la aplicación. Además, el archivo `atom-firebase-credentials.json` nunca se sube al repositorio de código fuente. Se utiliza un proceso de autenticación en Firebase para garantizar que las credenciales no estén expuestas.

### Patrones de Diseño

- **Singleton**: El servicio que interactúa con Firebase se configura como un Singleton para asegurar que solo exista una instancia de conexión a la base de datos durante la ejecución de la aplicación.
- **DTO (Data Transfer Object)**: Para transferir datos entre la capa de servicio y la capa de presentación (API RESTful), se utilizan objetos DTO que encapsulan la información que será enviada o recibida.
- **Factory**: Se utiliza el patrón Factory para crear la instancia de Firebase utilizando un archivo de configuración de credenciales.



### URL Api Alojada en Cloud Run

- **https://atombackendservice-44850752985.us-central1.run.app

## Instalación y Configuración

1. **Clonar el repositorio**:
   ```bash
   git clone https://github.com/tu_usuario/atom-backend-image.git

2. **Configurar Firebase:**:

-   Ir a Firebase Console.

-   Crear un nuevo proyecto o usa uno existente.

-   Habilitar Firestore y genera un archivo atom-firebase-credentials.json (como servicio de cuentas de Firebase).

-   Colocar el archivo atom-firebase-credentials.json en la raíz del proyecto.

3. **Configurar las variables de entorno:**:

-   Configura la variable de entorno FIREBASE_CREDENTIALS para que apunte a tu archivo atom-firebase-credentials.json.

En sistemas basados en UNIX (Linux, macOS):

   ```bash
export FIREBASE_CREDENTIALS=/path/to/atom-firebase-credentials.json
```
En Windows (cmd):

   ```bash
set FIREBASE_CREDENTIALS=C:\path\to\atom-firebase-credentials.json
```

4. **Construir el proyecto: Utiliza Maven para compilar y construir el proyecto:**:

   ```cmd
    ./mvnw clean install

5. **Ejecutar la aplicación: Para iniciar la aplicación localmente, usa:**:

```bash
./mvnw spring-boot:run
```

6. **Para subir cambios basta con hacer pull request a master, Cloud Build despliega automaticamente**:
