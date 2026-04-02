"use client";

import { useRouter } from "next/navigation";
import { useAuth } from "@/contexts/AuthContext";

interface Card {
  icon: React.ReactNode;
  tag: string;
  title: string;
  description: string;
  href: string;
}

/* ── Ícones Corrigidos ── */
function Icon({ children }: { children: React.ReactNode }) {
  return (
    <svg width="22" height="22" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="1.8" strokeLinecap="round" strokeLinejoin="round">
      {children}
    </svg>
  );
}

const icons = {
  store: (
    <Icon>
      <path d="M3 9l9-7 9 7v11a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z" />
      <path d="M9 22V12h6v10" />
    </Icon>
  ),
  userPlus: (
    <Icon>
      <path d="M16 21v-2a4 4 0 0 0-4-4H6a4 4 0 0 0-4 4v2" />
      <circle cx="9" cy="7" r="4" />
      <line x1="19" y1="8" x2="19" y2="14" />
      <line x1="22" y1="11" x2="16" y2="11" />
    </Icon>
  ),
  users: (
    <Icon>
      <path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2" />
      <path d="M23 21v-2a4 4 0 0 0-3-3.87" />
      <path d="M16 3.13a4 4 0 0 1 0 7.75" />
    </Icon>
  ),
  box: (
    <Icon>
      <path d="M21 16V8a2 2 0 0 0-1-1.73l-7-4a2 2 0 0 0-2 0l-7 4A2 2 0 0 0 3 8v8a2 2 0 0 0 1 1.73l7 4a2 2 0 0 0 2 0l7-4A2 2 0 0 0 21 16z" />
    </Icon>
  ),
  clipboard: (
    <Icon>
      <path d="M16 4h2a2 2 0 0 1 2 2v14a2 2 0 0 1-2 2H6a2 2 0 0 1-2-2V6a2 2 0 0 1 2-2h2" />
      <path d="M8 2h8a1 1 0 0 1 1 1v2a1 1 0 0 1-1 1H8a1 1 0 0 1-1-1V3a1 1 0 0 1 1-1z" />
    </Icon>
  ),
  grid: (
    <Icon>
      <rect x="3" y="3" width="7" height="7" />
      <rect x="14" y="3" width="7" height="7" />
      <rect x="14" y="14" width="7" height="7" />
      <rect x="3" y="14" width="7" height="7" />
    </Icon>
  ),
  briefcase: (
    <Icon>
      <path d="M20 7H4a2 2 0 0 0-2 2v10a2 2 0 0 0 2 2h16a2 2 0 0 0 2-2V9a2 2 0 0 0-2-2z" />
      <path d="M16 21V5a2 2 0 0 0-2-2h-4a2 2 0 0 0-2 2v16" />
    </Icon>
  ),
  wallet: (
    <Icon>
      <path d="M21 12V7H5a2 2 0 0 1 0-4h14v4M3 5v14a2 2 0 0 0 2 2h16v-5M18 12a2 2 0 0 0 0 4h4v-4Z" />
    </Icon>
  ),
  leaf: (
    <Icon>
      <path d="M11 20A7 7 0 0 1 9.8 6.1C15.5 5 17 4.48 19 2c1 2 2 4.18 2 8 0 5.5-4.78 10-10 10z" />
      <path d="M2 21c0-3 1.85-5.36 5.08-6C9.5 14.52 12 13 13 12" />
    </Icon>
  ),
  logout: (
    <Icon>
      <path d="M9 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h4" />
      <polyline points="16 17 21 12 16 7" />
      <line x1="21" y1="12" x2="9" y2="12" />
    </Icon>
  ),
  chevron: (
    <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round">
      <polyline points="9 18 15 12 9 6" />
    </svg>
  ),
};

const cadastrarCards: Card[] = [
  { icon: icons.store,     tag: "CADASTRAR", title: "Feira",        description: "Adicione novas feiras ao sistema",    href: "/feiras/nova" },
  { icon: icons.userPlus,  tag: "CADASTRAR", title: "Comerciante",  description: "Registre novos comerciantes",         href: "/comerciantes/novo" },
  { icon: icons.users,     tag: "CADASTRAR", title: "Cliente",      description: "Cadastre clientes na plataforma",     href: "/clientes/novo" },
  { icon: icons.box,       tag: "CADASTRAR", title: "Itens",        description: "Insira produtos e itens",             href: "/itens/novo" },
  { icon: icons.clipboard, tag: "VER",       title: "Pedidos",      description: "Acompanhe todos os pedidos",          href: "/pedidos" },
];

const gerenciarCards: Card[] = [
  { icon: icons.grid,      tag: "GERENCIAR", title: "Feira",        description: "Organize e edite as feiras",          href: "/feiras" },
  { icon: icons.briefcase, tag: "GERENCIAR", title: "Comerciante",  description: "Gerencie os comerciantes",            href: "/comerciantes" },
  { icon: icons.users,     tag: "GERENCIAR", title: "Cliente",      description: "Administre os clientes",              href: "/clientes" },
  { icon: icons.box,       tag: "GERENCIAR", title: "Itens",        description: "Edite o catálogo de produtos",        href: "/itens" },
  { icon: icons.wallet,    tag: "VER",       title: "Pagamentos",   description: "Controle financeiro e transações",    href: "/pagamentos" },
];

function DashboardCard({ card }: { card: Card }) {
  const router = useRouter();
  return (
    <button
      onClick={() => router.push(card.href)}
      className="relative bg-white border border-[#edf3f0] shadow-sm rounded-[20px] p-4 text-left flex flex-col overflow-hidden transition-all hover:shadow-md hover:-translate-y-1 hover:border-[#a7d4b8] active:scale-95 cursor-pointer h-full"
    >
      {/* Círculos decorativos (ligeiramente menores) */}
      <div className="absolute -top-6 -right-6 w-20 h-20 rounded-full bg-[#f4fbf7] pointer-events-none" />
      <div className="absolute -bottom-6 -right-6 w-16 h-16 rounded-full bg-[#f4fbf7] pointer-events-none" />

      {/* Ícone principal (Tamanho reduzido e menos margem) */}
      <div className="w-11 h-11 bg-[#f4fbf7] rounded-[14px] flex items-center justify-center text-[#358359] mb-3 z-10 shrink-0">
        {card.icon}
      </div>

      <span className="text-[10px] font-bold tracking-wider text-[#8fa898] uppercase z-10 mb-0.5">
        {card.tag}
      </span>
      <h3 className="text-base font-bold text-[#1f3025] leading-tight z-10 mb-1">
        {card.title}
      </h3>
      <p className="text-xs text-[#9aa9a1] leading-relaxed flex-1 z-10">
        {card.description}
      </p>

      {/* Seta no canto inferior direito */}
      <div className="self-end mt-2 w-7 h-7 rounded-full bg-[#f4fbf7] flex items-center justify-center text-[#358359] z-10 shrink-0">
        {icons.chevron}
      </div>
    </button>
  );
}

function SectionHeader({ icon, title, subtitle }: { icon: React.ReactNode; title: string; subtitle: string }) {
  return (
    <div className="flex items-center gap-3 mb-6 pb-3 border-b border-[#d1e8d8]">
      <div className="w-10 h-10 rounded-xl bg-[#1a4731] flex items-center justify-center text-white shrink-0">
        {icon}
      </div>
      <div>
        <h2 className="text-lg font-bold text-[#1a4731] leading-tight">{title}</h2>
        <p className="text-xs text-gray-500">{subtitle}</p>
      </div>
    </div>
  );
}

export default function DashboardPage() {
  const router = useRouter();
  const { logout, username } = useAuth();

  function handleLogout() {
    logout();
    router.push("/login");
  }

  return (
    <div className="min-h-screen bg-[#f0faf4] overflow-x-hidden" style={{ fontFamily: "'Segoe UI', sans-serif" }}>
      {/* Header */}
      <header className="sticky top-0 z-50 bg-gradient-to-r from-[#1a4731] to-[#2d7a4f] flex items-center justify-between px-4 md:px-8 h-14 md:h-16">
        <div className="flex items-center gap-2.5">
          <div className="w-8 h-8 rounded-lg bg-white/15 flex items-center justify-center text-white">
            {icons.leaf}
          </div>
          <span className="text-white font-bold text-base md:text-lg">EcoFeira</span>
        </div>

        <span className="hidden md:flex items-center gap-1.5 text-white/70 text-sm">
          Sistema Ecofeira Protótipo
          <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2"><polyline points="6 9 12 15 18 9"/></svg>
        </span>

        <div className="flex items-center gap-2 md:gap-3">
          {username && (
            <span className="hidden sm:block text-white/70 text-xs">{username}</span>
          )}
          <button
            onClick={handleLogout}
            className="flex items-center gap-1.5 bg-white/10 hover:bg-white/20 border border-white/20 rounded-lg text-white text-xs md:text-sm font-medium px-3 py-1.5 md:px-4 md:py-2 transition"
          >
            {icons.logout}
            <span>Sair</span>
          </button>
        </div>
      </header>

      {/* Conteúdo */}
      <main className="max-w-6xl mx-auto px-4 md:px-8 py-8 md:py-12 flex flex-col gap-12">
        {/* Seção Cadastrar */}
        <section>
          <SectionHeader
            icon={icons.userPlus}
            title="Cadastrar"
            subtitle="Adicione novos registros ao sistema"
          />
          <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 xl:grid-cols-5 gap-4 md:gap-5">
            {cadastrarCards.map((card) => (
              <DashboardCard key={card.title + card.tag} card={card} />
            ))}
          </div>
        </section>

        {/* Seção Gerenciar */}
        <section>
          <SectionHeader
            icon={icons.grid}
            title="Gerenciar"
            subtitle="Edite e administre os dados existentes"
          />
          <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 xl:grid-cols-5 gap-4 md:gap-5">
            {gerenciarCards.map((card) => (
              <DashboardCard key={card.title + card.tag} card={card} />
            ))}
          </div>
        </section>
      </main>
    </div>
  );
}