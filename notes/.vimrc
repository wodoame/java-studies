" --- General Settings ---
set nocompatible            " Disable compatibility with old vi
filetype plugin indent on   " Enable detection, plugins, and indenting for filetypes
syntax on                   " Enable syntax highlighting (This setting doesn't work on this version)
set encoding=utf-8          " Standard encoding
"set scriptencoding=utf-8 
set clipboard=unnamedplus

" --- UI & Numbers ---
set number                  " Show absolute line number
set rnu                     " Show relative line number (Combined with 'number' for Hybrid mode)
"set cursorline              " Highlight the current line
set showmode                " Display the current mode (Insert, Visual, etc.)
set laststatus=2            " Always show the status line
set wildmenu                " Visual autocomplete for command menu
set lazyredraw              " Redraw screen only when needed (speeds up macros)

" --- Search Settings ---
set incsearch               " Search as characters are entered
set hlsearch                " Highlight search matches
set ignorecase              " Ignore case when searching...
set smartcase               " ...unless there is a capital letter

" --- Indentation & Tabs ---
set expandtab               " Convert tabs to spaces
set shiftwidth=4            " Number of spaces for auto-indent
set softtabstop=4           " Number of spaces per tab while editing
set tabstop=4               " Number of spaces that a <Tab> in the file counts for
set autoindent              " Copy indent from current line when starting a new one

" --- Backups & History ---
set hidden                  " Allow switching buffers without saving
set history=1000            " Remember more commands and search history
set undofile                " Maintain undo history between sessions
set nobackup                " Most people prefer to rely on Git; less clutter
set noswapfile

" --- Quality of Life ---
set backspace=indent,eol,start " Fix backspace behavior on many systems
set scrolloff=8             " Keep at least 8 lines visible above/below cursor
set splitbelow              " Open new horizontal splits below
set splitright              " Open new vertical splits to the right
