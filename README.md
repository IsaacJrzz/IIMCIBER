# SDAS – Security Detection & Analysis System

<p align="center">
  <img src="https://img.shields.io/badge/SDAS-Security%20Detection%20&%20Analysis%20System-black">
  <img src="https://img.shields.io/badge/Java-17+-blue">
  <img src="https://img.shields.io/badge/Status-Operational-green">
</p>

Sistema didáctico en Java orientado a la detección simulada de amenazas mediante tres módulos principales:

- **Módulo 1 – Integridad de ficheros**
- **Módulo 2 – Análisis de tráfico de red**
- **Módulo 3 – Procesos y malware simulado**

El objetivo es mostrar cómo un sistema de ciberseguridad puede vigilar, detectar y registrar eventos sospechosos, manteniendo logs y clasificando comportamientos sospechosos.

---

## 📦 Arquitectura del proyecto

core/
SDASService.java
SDASLogger.java
integrity/
IntegrityMonitor.java
FileInfo.java
network/
TrafficAnalyzer.java
process/
ProcessSimulator.java
ProcessAnalyzer.java
CyberSecurityMonitor.java


---

## 🚀 Ejecución del sistema

Ejecutable principal:

CyberSecurityMonitor.java


Este coordina los tres módulos de forma concurrente mediante `ScheduledExecutorService`.

---

## 🔍 Módulos

### ✔ Módulo 1 — Integridad

- Supervisa la carpeta `watch/`
- Detecta:
  - creación de ficheros
  - modificación de ficheros
  - eliminación de ficheros
- Registra eventos en el log general

Implementa hashing **SHA-256** para detectar alteraciones.

---

### ✔ Módulo 2 — Tráfico simulado

Genera tráfico en:

data/traffic.log


Simula puertos:

- **Normales:** 80, 443
- **Sospechosos / intrusión:** 22, 23, 135, 4444

---

### ✔ Módulo 3 — Procesos + malware

Genera procesos en:

data/processes.log


Incluye procesos de sistema y malware ficticio:

miner.exe
keylogger.exe
ransomware.exe
backup_agent.exe


Detecta comportamientos e imprime alertas.

---

## 🗂 Directorios necesarios

Antes de ejecutar, deben existir:

data/
watch/


> Según el entorno se pueden crear automáticamente o manualmente.

---

## 📝 Logs generados

Durante la ejecución se generan:

log_sdas.txt (log principal)
data/processes.log
data/traffic.log


Estos sirven de evidencia para análisis posterior.

---

## ▶ Cómo ejecutar

### Desde IDE:

- Ejecutar `CyberSecurityMonitor`

### Desde terminal:

```sh
javac *.java
java CyberSecurityMonitor
```
(Dependiendo de la estructura real del proyecto)

## 🎯 Objetivo académico

Proyecto orientado al aprendizaje en:

monitorización de sistemas

ciberseguridad

modularidad

concurrencia

análisis de eventos y alertas

No es un IDS real, sino una maqueta simulada para docencia/demostración.

## ✔ Estado del proyecto

Módulos implementados y operativos

Logs generados correctamente

Entradas para capturas preparadas
