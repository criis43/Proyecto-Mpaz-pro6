# MPAZ PRO 6.0 - Sistema de Gestión Escolar

Aplicación móvil desarrollada en Android con **Kotlin** y **Jetpack Compose** para la gestión pedagógica y de revisión de material educativo entre Docentes, UTP y Estudiantes.

## Integrantes del Equipo
* **Cristian Zavala**
* **Benjamin Soto**

---

##  Tecnologías y Arquitectura Integradas

* **UI & Layouts:** Jetpack Compose con el sistema de diseño **Material 3**.
* **Navegación:** `Navigation Compose` con gestión de back-stack y navegación basada en roles.
* **Patrón Arquitectónico (IE 2.2.1):** Arquitectura **MVVM** con `ViewModel` para el desacoplamiento de la lógica de negocio y UI.
* **Persistencia Local (IE 2.3.1):** Almacenamiento con `SharedPreferences` para conservar las autorizaciones pedagógicas ante reinicios de la app.
* **Recursos Nativos del Dispositivo (IE 2.4.1):**
    1. **Notificaciones del Sistema:** Envío de notificaciones nativas al aprobar/rechazar material en UTP.
    2. **Galería de Imágenes:** Acceso a las imágenes del dispositivo para cambiar la foto de perfil en los paneles mediante `Coil`.
* **Formularios y Validaciones (IE 2.1.2):** Pantalla de registro con validaciones en tiempo real por campo, manejo de errores visuales e íconos informativos.
* **Animaciones (IE 2.2.2):** Transiciones fluidas de visibilidad (`AnimatedVisibility`) y cambios suaves de color (`animateColorAsState`).

---

## Requisitos para Ejecutar el Proyecto

1. **Android Studio** Jellyfish / Iguana o superior.
2. **JDK:** Java 17 / Kotlin 1.9+.
3. **SDK Mínimo:** Android 8.0 (API 26).
4. **Dispositivo/Emulador recomendado:** Android 13 (API 33) o superior para visualizar permisos de notificación.

### Pasos de Ejecución
1. Clonar el repositorio:
   ```bash
   git clone <https://github.com/criis43/Proyecto-Mpaz-pro6>