module.exports = {
    base: '/M/',
    title: 'Project M',
    description: 'A data-driven programming language for game developers',
    theme: 'yuu',
    themeConfig: {
        yuu: {
            defaultDarkTheme: true,
        },
        logo: 'https://avatars2.githubusercontent.com/u/6147446?s=400&u=c325f5bfc187e889f05cc872681ad1cb1ba6b3b5&v=4',
        nav: [{
                text: 'Starter guide',
                link: '/starter-guide/set-up'
            },
            {
                text: 'User guide',
                link: '/user-guide/standard-library'
            },
            {
                text: 'Developer guide',
                link: '/developer-guide/design-decisions'
            }
        ],
        sidebar: {
            '/starter-guide/': [
                'set-up',
                'your-first-project',
                'personalize'
            ],
            '/user-guide/': [
                'standard-library',
                {
                    title: 'Guided examples',
                    children: [
                        'guided-examples/pong',
                        'guided-examples/space-invaders'
                    ]
                },
                'troubleshooting'
            ],
            '/developer-guide/': [
                'design-decisions',
                'setting-up-the-development-environment',
                'structure-of-the-project',
                'contributing'
            ]

        },
        lastUpdated: 'Last Updated'
    }
}