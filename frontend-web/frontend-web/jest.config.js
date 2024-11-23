module.exports = {
    preset: 'jest-preset-angular',
    testEnvironment: 'jsdom',
    setupFilesAfterEnv: ['<rootDir>/src/test-setup.ts'],
    transform: {
      '^.+\\.(ts|js|html)$': 'jest-preset-angular',
    },
  };
  