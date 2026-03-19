# Sistema de Administración de Cuentas Bancarias

> Proyecto de software desarrollado en Java enfocado en la aplicación práctica de Programación Orientada a Objetos (POO) y el dominio del **Java Collections Framework**.

Este sistema simula el entorno de trabajo de un cajero bancario, permitiendo la gestión integral de clientes, operaciones transaccionales y la proyección de rendimientos mediante cuentas de inversión.

## Descripción General

El objetivo principal de este proyecto es diseñar una arquitectura robusta y escalable sin depender de bases de datos externas, gestionando el estado y la persistencia en memoria mediante el uso estratégico de colecciones (`List`, `Set`, `Map`). 

El sistema está diseñado en capas (separando la lógica de negocio de la interfaz de usuario), lo que permite una transición limpia de una interfaz por línea de comandos (CLI) a una Interfaz Gráfica de Usuario (GUI).

## Características y Funcionalidades

- **Gestión de Clientes:** Registro de nuevos usuarios y modificación de datos personales. Búsqueda optimizada en tiempo constante $O(1)$.
- **Operaciones de Caja (Cuentas Básicas):** Depósitos y retiros con validación de fondos y actualización de saldos en tiempo real.
- **Módulo de Inversiones:** Creación de cuentas de inversión mediante transferencia de fondos desde la cuenta básica. Implementa cálculo de rendimientos basados en plazos definidos (simulación de fechas).
- **Historial Transaccional:** Registro inmutable y cronológico de cada movimiento (depósitos, retiros, apertura de inversiones).
- **Módulo de Crédito (Feature Extra):** Gestión de tarjetas de crédito con control de límite y saldo deudor.

## Arquitectura y Estructuras de Datos (Collections)

Para garantizar la integridad y eficiencia de los datos, el modelo de negocio implementa las siguientes estructuras:

* **`Map<String, Cliente>` (Tabla Hash):** Utilizado en el Controlador del banco para almacenar el directorio de clientes. Permite que el cajero localice la información de un cliente de forma inmediata utilizando su Número de Cliente como llave.
* **`Set<Cuenta>` (Conjuntos):** Implementado en la entidad Cliente para almacenar sus productos financieros (Cuentas Básicas e Inversiones). Garantiza matemáticamente que no existan cuentas duplicadas asignadas a un mismo titular.
* **`List<Movimiento>` (Listas):** Utilizado en el interior de cada Cuenta para almacenar el historial de transacciones. Asegura que los movimientos mantengan su orden cronológico exacto para la generación de estados de cuenta.

## Tecnologías Utilizadas

- **Lenguaje:** Java (JDK)
- **Paradigma:** Programación Orientada a Objetos (POO)
- **Entorno de Desarrollo (IDE):** Apache NetBeans
- **Control de Versiones:** Git & GitHub
- **Documentación Técnica:** Javadoc y LaTeX

## 👥 Equipo de Desarrollo

Proyecto desarrollado para la asignatura de Programación Orientada a Objetos de la Facultad de Ingeniería (UNAM).

* **Francisco José Coutiño Morales** - *Arquitectura Core y Lógica Transaccional* - [Mi GitHub](https://github.com/FranciscoCou077)
* **[Nombre de tu compañero 2]** - *Implementación de Inversiones, Fechas y Documentación* - [Su GitHub o LinkedIn]
* **[Nombre de tu compañero 3]** - *Controlador MVC, Interfaz GUI y Módulo de Crédito* - [Su GitHub o LinkedIn]
## Instalación y Uso (Para Desarrolladores)

1. Clonar este repositorio en tu máquina local:
   ```bash
   git clone [https://github.com/TU_USUARIO/Sistema-Bancario-Java.git](https://github.com/TU_USUARIO/Sistema-Bancario-Java.git)
