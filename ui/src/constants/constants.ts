export const __API__ = process.env.NODE_ENV === 'development'
    ? process.env.REACT_APP_TESLER_API_URL_DEV
    : process.env.REACT_APP_TESLER_API_URL
