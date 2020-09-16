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
                link: '/0. Starter guide/0. Set up'
            },
            {
                text: 'User guide',
                link: '/1. User guide/0. Standard library'
            },
            {
                text: 'Developer guide',
                link: '/2. Developer guide/0. Design decisions'
            }
        ],
        sidebar: {
            '/0. Starter guide/': [
                '0. Set up',
                '1. Your first project',
                '2. Personalize'
            ],
            '/1. User guide/': [
                '0. Standard library',
                {
                    title: 'Guided examples',
                    children: [
                        '1. Guided examples/0. Pong',
                        '1. Guided examples/1. Space invaders'
                    ]
                },
                '2. Troubleshooting'
            ],
            '/2. Developer guide/': [
                '0. Design decisions',
                '1. Setting up the development environment',
                '2. Structure of the project',
                '3. Contributing'
            ]

        },
        lastUpdated: 'Last Updated'
    }
}