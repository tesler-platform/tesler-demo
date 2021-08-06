import Keycloak, { KeycloakInitOptions } from 'keycloak-js'

export const KEYCLOAK_MIN_VALIDITY = 10

export const keycloakOptions: KeycloakInitOptions = { onLoad: 'login-required' }

export const keycloak = Keycloak('keycloak.json')
