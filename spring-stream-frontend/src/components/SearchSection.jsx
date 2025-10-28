import { Search } from 'lucide-react';

export default function SearchSection({ searchInput, setSearchInput, onPlayVideo }) {
  return (
    <div>
      <h3 className="text-md font-semibold text-gray-900 mb-2">Search Video</h3>
      <div className="flex gap-2">
        <input
          type="text"
          value={searchInput}
          onChange={(e) => setSearchInput(e.target.value)}
          onKeyPress={(e) => e.key === 'Enter' && onPlayVideo()}
          placeholder="Enter video ID..."
          className="flex-1 px-3 py-2 border border-gray-300 rounded-md text-sm focus:outline-none focus:ring-2 focus:ring-blue-500"
        />
        <button
          onClick={onPlayVideo}
          className="px-4 py-2 bg-blue-600 text-white rounded-md text-sm font-medium hover:bg-blue-700 transition"
        >
          <Search className="w-4 h-4" />
        </button>
      </div>
    </div>
  );
}